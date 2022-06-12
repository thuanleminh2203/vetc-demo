package com.mbbank.vetc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbbank.vetc.component.SOAPConnector;
import com.mbbank.vetc.exception.BadRequestException;
import com.mbbank.vetc.payload.request.AccountInfoRequest;
import com.mbbank.vetc.payload.response.AccountInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AccountInfoServiceImpl implements AccountInfoService {
    @Value("${vetc.username}")
    private String USERNAME;

    @Value("${vetc.password}")
    private String PASSWORD;

    private final SOAPConnector soapConnector;
    private final ObjectMapper mapper;


    @Override
    @SneakyThrows
    public AccountInfoResponse getAccountInfo(AccountInfoRequest request) {
        if (StringUtils.isEmpty(request.getAccountNo()) && StringUtils.isEmpty(request.getPlateNumber())) {
            throw new BadRequestException("{accountNo.or.plateNumber.is.required}");
        }

        var info = soapConnector.getAccountInfo(request);
        return mapper.convertValue(info,AccountInfoResponse.class);
    }

//    @PostConstruct
    private void init(){
        AccountInfoRequest request = new AccountInfoRequest();
        request.setAccountNo("E0100001272");
        getAccountInfo(request);
    }
}


