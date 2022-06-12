package com.mbbank.vetc.feign;

import com.mbbank.vetc.feign.interceptor.PaymentHubInterceptor;
import com.mbbank.vetc.feign.request.InitTransactionFeignRequest;
import com.mbbank.vetc.feign.request.VerifyFeignRequest;
import com.mbbank.vetc.feign.response.InitTransactionFeignResponse;
import com.mbbank.vetc.feign.response.TransactionFeignResponse;
import com.mbbank.vetc.feign.response.UserFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "paymenthub-client", url = "${mb.oauth2.paymentHub.baseUrl}", configuration = PaymentHubInterceptor.class)
public interface PaymentHubClient {

    @PostMapping("/api/merchant/v1/session/verify")
    UserFeignResponse verify(@RequestBody VerifyFeignRequest request);


    @PostMapping("/api/merchant/v1/transaction")
    InitTransactionFeignResponse initTransaction(@RequestBody InitTransactionFeignRequest request);

    @GetMapping("/api/merchant/v1/transaction/{transactionId}")
    TransactionFeignResponse getTransaction(@PathVariable String transactionId);
}
