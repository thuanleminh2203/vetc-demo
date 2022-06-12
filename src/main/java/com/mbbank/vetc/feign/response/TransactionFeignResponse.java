package com.mbbank.vetc.feign.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionFeignResponse {
    private String status;
    private LocalDateTime paidTime;
}
