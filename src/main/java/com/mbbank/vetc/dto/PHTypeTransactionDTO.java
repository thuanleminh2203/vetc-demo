package com.mbbank.vetc.dto;

import lombok.Data;

@Data
public class PHTypeTransactionDTO {
    private String code;
    private String name;
    private Boolean allowCard;
}
