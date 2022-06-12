package com.mbbank.vetc.controller;

import com.mbbank.vetc.payload.request.AccountInfoRequest;
import com.mbbank.vetc.payload.response.AccountInfoResponse;
import com.mbbank.vetc.service.AccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Api(tags = "Account controller")
@RequiredArgsConstructor
public class AccountController {

    private final AccountInfoService accountInfoService;

    @ApiOperation(value = "Get account info")
    @GetMapping(value = "/info")
    public AccountInfoResponse getAccountInfo(@Valid AccountInfoRequest request) {
        return accountInfoService.getAccountInfo(request);
    }



}