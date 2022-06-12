package com.mbbank.vetc.feign.response;

import com.mbbank.vetc.dto.PHTypeTransactionDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InitTransactionFeignResponse {

//    @JsonProperty("id")
    private String id;
    private String cif;
    private BigDecimal amount;
    private String description;
    private PHTypeTransactionDTO type;
    private LocalDateTime createdTime;
    private LocalDateTime paidTime;
    private String status;
    private String fundingSource;
    private String cardType;
}
