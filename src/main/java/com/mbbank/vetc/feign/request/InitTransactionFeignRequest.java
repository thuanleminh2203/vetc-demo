package com.mbbank.vetc.feign.request;

import com.mbbank.vetc.constant.AppConstants;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InitTransactionFeignRequest {
    private BigDecimal amount;
    private String cif;
    private final String type = AppConstants.VETC_TRANSACTION_TYPE;
    private final String description = AppConstants.VETC_DESCRIPTION;
    private String sessionId;
    private final boolean allowCard = true;
}
