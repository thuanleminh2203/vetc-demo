package com.mbbank.vetc.payload.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionCallbackRequest {
    private String id;
    private String cif;
    private BigDecimal amount;
    private String status;
    private LocalDateTime paidTime;
    private String checksum;
}
