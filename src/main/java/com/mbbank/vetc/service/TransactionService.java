package com.mbbank.vetc.service;

import com.mbbank.vetc.config.UserPrincipal;
import com.mbbank.vetc.dto.TransactionDTO;
import com.mbbank.vetc.payload.request.HistoryTransactionRequest;
import com.mbbank.vetc.payload.request.InitTransactionRequest;
import com.mbbank.vetc.payload.request.TransactionCallbackRequest;
import com.mbbank.vetc.payload.response.InitTransactionResponse;
import com.mbbank.vetc.payload.response.PageResponse;

import java.util.List;

public interface TransactionService {

    InitTransactionResponse initTransaction(InitTransactionRequest request, UserPrincipal currentUser);
    void callbackTransaction(TransactionCallbackRequest request);
    PageResponse<List<TransactionDTO>> getHistory(HistoryTransactionRequest request, UserPrincipal currentUser);
}
