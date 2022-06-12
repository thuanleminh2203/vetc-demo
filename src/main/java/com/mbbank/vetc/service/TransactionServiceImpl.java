package com.mbbank.vetc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbbank.vetc.component.SOAPConnector;
import com.mbbank.vetc.config.UserPrincipal;
import com.mbbank.vetc.constant.AppConstants;
import com.mbbank.vetc.dto.PageDTO;
import com.mbbank.vetc.dto.TransactionDTO;
import com.mbbank.vetc.dto.TransactionsDTO;
import com.mbbank.vetc.entity.TransactionEntity;
import com.mbbank.vetc.enums.ProcessEnum;
import com.mbbank.vetc.exception.BadRequestException;
import com.mbbank.vetc.feign.PaymentHubClient;
import com.mbbank.vetc.feign.request.InitTransactionFeignRequest;
import com.mbbank.vetc.payload.request.HistoryTransactionRequest;
import com.mbbank.vetc.payload.request.InitTransactionRequest;
import com.mbbank.vetc.payload.request.TransactionCallbackRequest;
import com.mbbank.vetc.payload.response.InitTransactionResponse;
import com.mbbank.vetc.payload.response.PageResponse;
import com.mbbank.vetc.repository.TransactionRepository;
import com.mbbank.vetc.util.FunctionUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final PaymentHubClient paymentHubClient;
    private final TransactionRepository transactionRepository;
    private final ObjectMapper mapper;
    private final MongoTemplate mongoTemplate;
    private final SOAPConnector soapConnector;

    @Override
    public InitTransactionResponse initTransaction(InitTransactionRequest request, UserPrincipal currentUser) {
        InitTransactionResponse res = new InitTransactionResponse();
        var feignRequest = new InitTransactionFeignRequest();
        feignRequest.setAmount(new BigDecimal(request.getAmount()));
        feignRequest.setSessionId(currentUser.getSessionId());
        feignRequest.setCif(currentUser.getCif());
        var initTransactionFeignResponse = paymentHubClient.initTransaction(feignRequest);
        var entity = mapper.convertValue(initTransactionFeignResponse, TransactionEntity.class);
        entity.setIdPHTransaction(initTransactionFeignResponse.getId());
        entity.setId(null);
        entity.setAccountNo(request.getAccountNo());
        transactionRepository.save(entity);
        res.setTransactionId(initTransactionFeignResponse.getId());
        return res;
    }

    @Override
    public void callbackTransaction(TransactionCallbackRequest request) {
        var checkChecksum = FunctionUtils.checkChecksum(request);
        if (checkChecksum) {
            transactionRepository.findByIdPHTransaction(request.getId()).ifPresent(k -> {
                if (ProcessEnum.INIT.equals(k.getProcess())) {

                    var isPayment = soapConnector.doPayment(request, k.getAccountNo());
                    k.setPaidTime(request.getPaidTime());
                    k.setStatus(request.getStatus());
                    k.setProcess(isPayment ? ProcessEnum.DONE : ProcessEnum.PAID);
                    transactionRepository.save(k);
                }
            });

        }
    }


    @Override
    @SneakyThrows
    public PageResponse<List<TransactionDTO>> getHistory(HistoryTransactionRequest request, UserPrincipal currentUser) {
        var startDate = request.getStartDate();
        var endDate = request.getEndDate();
        if (startDate.isAfter(endDate))
            throw new BadRequestException("{endDate.must.be.greater.than.startDate}");

        Criteria criteria = Criteria.where("cif").is(currentUser.getCif());
        criteria.and("createdAt").gte(request.getStartDate()).lte(request.getEndDate());
        criteria.and("process").is(ProcessEnum.DONE);
        var match = Aggregation.match(criteria);

        var skip = Aggregation.skip((request.getPageIndex() - 1) * request.getPageSize());
        var limit = Aggregation.limit(request.getPageSize());
        var sort = Aggregation.sort(Sort.Direction.DESC, "createdAt");
        var facet = Aggregation.facet(match, Aggregation.count().as("total")).as("total").and(match, sort, skip, limit).as("result");
        var project = Aggregation.project("result").and(ArrayOperators.ArrayElemAt.arrayOf("total.total").elementAt(0)).as("total");

        var aggregation = Aggregation.newAggregation(facet, project);
        var aggregateResult = mongoTemplate.aggregate(aggregation, TransactionEntity.class, TransactionsDTO.class);
        var transactionsDTO = aggregateResult.getUniqueMappedResult();
        PageResponse<List<TransactionDTO>> response = new PageResponse<>();

        response.setPage(new PageDTO(request.getPageIndex(), request.getPageSize(), transactionsDTO != null ? transactionsDTO.getTotal() : 0));
        response.setResult(transactionsDTO != null ? transactionsDTO.getResult() : Collections.emptyList());

        return response;

    }


    /**
     * schedule run every 4h
     * if process equal INIT => call PH => call VETC => update record
     */
    @Scheduled(fixedDelayString = "${time.schedule.fixedDelay}", initialDelayString = "${time.schedule.initialDelay}")
//    @PostConstruct
    private void scheduleRetries() {
        log.info("===Start run schedule job retry at {}===", LocalDateTime.now());
        var transactions = transactionRepository.findAllByProcessInAndCreatedAtLessThanEqual(List.of(ProcessEnum.INIT, ProcessEnum.PAID), LocalDateTime.now().plusHours(-1));
        var request = new TransactionCallbackRequest();

        for (TransactionEntity transaction : transactions) {
            try {
                if (ProcessEnum.INIT.equals(transaction.getProcess())) {
                    var transactionFeignResponse = paymentHubClient.getTransaction(transaction.getIdPHTransaction());
                    var status = transactionFeignResponse.getStatus();
                    if (AppConstants.PH_STATUS_PENDING.equals(status)) {
                        transaction.setProcess(ProcessEnum.CANCEL);
                    } else {
                        request.setAmount(transaction.getAmount());
                        request.setPaidTime(transaction.getPaidTime());
                        request.setId(transaction.getIdPHTransaction());
                        var isPayment = soapConnector.doPayment(request, transaction.getAccountNo());
                        transaction.setProcess(isPayment ? ProcessEnum.DONE : ProcessEnum.PAID);
                        transaction.setPaidTime(transactionFeignResponse.getPaidTime());
                    }
                } else {

                    if (soapConnector.checkTransactionHistory(transaction.getIdPHTransaction())) {
                        transaction.setProcess(ProcessEnum.DONE);

                    } else {
                        request.setAmount(transaction.getAmount());
                        request.setPaidTime(transaction.getPaidTime());
                        request.setId(transaction.getIdPHTransaction());
                        var isPayment = soapConnector.doPayment(request, transaction.getAccountNo());
                        transaction.setProcess(isPayment ? ProcessEnum.DONE : ProcessEnum.PAID);

                    }
                }
                transactionRepository.save(transaction);
                log.info("===Retry transactionID: {} success at {}===", transaction.getIdPHTransaction(), LocalDateTime.now());

            } catch (Exception e) {
                log.error("===Error when retry transactionID: {} at {} reason : {}===", transaction.getIdPHTransaction(), LocalDateTime.now(), e);

            }
        }
        log.info("===End run schedule job retry at {}===", LocalDateTime.now());
    }

//    public void demo(TransactionEntity transactionEntity){
//        transactionEntity.setId("123123");
//
//    }
//
//    @PostConstruct
//    public void init(){
//        TransactionEntity e = new TransactionEntity();
//        demo(e);
//        System.out.println("==============" + e.getIdPHTransaction());
//    }

}
