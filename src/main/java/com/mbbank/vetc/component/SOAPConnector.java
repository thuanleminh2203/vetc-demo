package com.mbbank.vetc.component;


import com.mbbank.vetc.config.SoapClientSecurityHeaderWriter;
import com.mbbank.vetc.constant.AppConstants;
import com.mbbank.vetc.genarate.imp.AccountInfo;
import com.mbbank.vetc.genarate.imp.PartnerInfo;
import com.mbbank.vetc.genarate.imp.QueryAccountInfo;
import com.mbbank.vetc.genarate.imp.QueryAccountInfoResponse;
import com.mbbank.vetc.genarate.imp.QueryRechargeTrans;
import com.mbbank.vetc.genarate.imp.QueryRechargeTransResponse;
import com.mbbank.vetc.genarate.imp.RechargeSignature;
import com.mbbank.vetc.genarate.imp.RechargeSignatureResponse;
import com.mbbank.vetc.genarate.imp.RechargeTransSignature;
import com.mbbank.vetc.payload.request.AccountInfoRequest;
import com.mbbank.vetc.payload.request.TransactionCallbackRequest;
import com.mbbank.vetc.util.VETCSignAndVerify;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class SOAPConnector extends WebServiceGatewaySupport {

    @Value("${vetc.api}")
    private String API;

    @Value("${vetc.publicKey}")
    private String VETC_PUBLIC_KEY;

    @Autowired
    SoapClientSecurityHeaderWriter soapClientSecurityHeaderWriter;

    @Autowired
    VETCSignAndVerify vetcSignAndVerify;

    public AccountInfo getAccountInfo(AccountInfoRequest request) {
        QueryAccountInfo info = new QueryAccountInfo();
        info.setAccountNo(request.getAccountNo());
        info.setPlateNumber(request.getPlateNumber());
        JAXBElement<QueryAccountInfoResponse> response = (JAXBElement<QueryAccountInfoResponse>) getWebServiceTemplate().marshalSendAndReceive(API, info, soapClientSecurityHeaderWriter);
        var queryAccountInfoResponse = response.getValue();
        var accountInfo = queryAccountInfoResponse.getReturn();
        return accountInfo;
    }



    /**
     * if payment with VETC success => true, any other case => false
     *
     * @param request
     * @param accountNo
     * @return
     */
    @SneakyThrows
    public boolean doPayment(TransactionCallbackRequest request, String accountNo) {
        final String refId = request.getId();
        boolean result = false;
        try {

            RechargeSignature rechargeSignature = new RechargeSignature();
            rechargeSignature.setAccountNo(accountNo);
            rechargeSignature.setAmount(request.getAmount().doubleValue());

            String paidTime = formatDate(request.getPaidTime(), AppConstants.DATE_FORMATTER);
            rechargeSignature.setTransDate(paidTime);

            PartnerInfo partnerInfo = new PartnerInfo();
            partnerInfo.setChannelType(AppConstants.VETC_CHANNEL_TYPE);
            partnerInfo.setRefId(refId);

            rechargeSignature.setPartnerInfo(partnerInfo);

//        String signatureData = new StringBuilder(accountNo).append("|").append(refId).append("|").append(paidTime).toString();
            String signatureData = generateSignatureData(accountNo, refId, paidTime);

            var signature = vetcSignAndVerify.sign(signatureData);

            rechargeSignature.setSignature(signature);

            JAXBElement<RechargeSignatureResponse> response = (JAXBElement<RechargeSignatureResponse>) getWebServiceTemplate().marshalSendAndReceive(API, rechargeSignature, soapClientSecurityHeaderWriter);
            var rechargeSignatureResponse = response.getValue();
            var rechargeTransSignature = rechargeSignatureResponse.getReturn();
            //check signature's response
            result = verify(rechargeTransSignature);

            if (!result)
                log.error("===Error when authentication signatureData in transactionID: {} from VETC===", request.getId());
        } catch (Exception e) {
            log.error("===Error when payment with VETC in transactionID: {} at {}===", request.getId(), LocalDateTime.now());

        }

        return result;


    }

    /**
     * check transaction history
     * @param refId
     * @return true if successful
     */
    public boolean checkTransactionHistory(String refId){
        QueryRechargeTrans request = new QueryRechargeTrans();
        request.setRefId(refId);
        JAXBElement<QueryRechargeTransResponse> response = (JAXBElement<QueryRechargeTransResponse>) getWebServiceTemplate().marshalSendAndReceive(API,request, soapClientSecurityHeaderWriter);
        var queryRechargeTransResponse = response.getValue();
        return queryRechargeTransResponse.getReturn().getRechargeGwId() != -1;
    }

    private String formatDate(LocalDateTime data, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return data.format(formatter);
    }

    private String generateSignatureData(String accountNo, String refId, String paidTime) {
        return new StringBuilder(accountNo).append("|").append(refId).append("|").append(paidTime).toString();

    }

    private boolean verify(RechargeTransSignature transSignature) {
        String signatureData = new StringBuilder(String.valueOf(transSignature.getRechargeTransId()))
                .append("|")
                .append(formatDate(transSignature.getRechargeTransDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime(), AppConstants.DATE_FORMATTER))
                .toString();
        return vetcSignAndVerify.verify(signatureData, transSignature.getSignature());
    }


}
