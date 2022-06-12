package com.mbbank.vetc.controller;

import com.mbbank.vetc.config.UserPrincipal;
import com.mbbank.vetc.dto.TransactionDTO;
import com.mbbank.vetc.payload.request.HistoryTransactionRequest;
import com.mbbank.vetc.payload.request.InitTransactionRequest;
import com.mbbank.vetc.payload.request.OauthRequest;
import com.mbbank.vetc.payload.request.TransactionCallbackRequest;
import com.mbbank.vetc.payload.response.InitTransactionResponse;
import com.mbbank.vetc.payload.response.OauthResponse;
import com.mbbank.vetc.payload.response.PageResponse;
import com.mbbank.vetc.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@Api(tags = "Transaction controller")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(value = "Api init transaction")
    @PostMapping("/init")
    public InitTransactionResponse initTransaction(@Valid @RequestBody InitTransactionRequest request,
                                                   @ApiIgnore @AuthenticationPrincipal UserPrincipal currentUser) {
        return transactionService.initTransaction(request,currentUser);
    }

    @ApiOperation(value = "Api callback transaction")
    @PostMapping("/callback")
    public void initTransaction(@RequestBody TransactionCallbackRequest request) {
        transactionService.callbackTransaction(request);
    }

    @ApiOperation(value = "Api get history's transaction")
    @GetMapping("/history")
    public PageResponse<List<TransactionDTO>> getHistory(@Valid HistoryTransactionRequest request,
                                                         @ApiIgnore @AuthenticationPrincipal UserPrincipal currentUser) {
       return transactionService.getHistory(request,currentUser);
    }
}
