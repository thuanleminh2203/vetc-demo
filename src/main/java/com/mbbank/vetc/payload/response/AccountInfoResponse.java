package com.mbbank.vetc.payload.response;

import lombok.Data;

@Data
public class AccountInfoResponse {
    private String accountName;
    private String accountNo;
    private String accountType;
    private String status;
}
