package com.mbbank.vetc.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {

    private String id;
    private String idPHTransaction;
    private BigDecimal amount;
    private LocalDateTime createdAt;

}
