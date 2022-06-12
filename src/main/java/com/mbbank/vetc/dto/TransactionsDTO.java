package com.mbbank.vetc.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionsDTO {
    private long total;
    private List<TransactionDTO> result;
}
