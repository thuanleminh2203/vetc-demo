package com.mbbank.vetc.service;

import com.mbbank.vetc.dto.AccountInfoDTO;
import com.mbbank.vetc.payload.request.AccountInfoRequest;
import com.mbbank.vetc.payload.response.AccountInfoResponse;

public interface AccountInfoService {

    AccountInfoResponse getAccountInfo(AccountInfoRequest request);
}
