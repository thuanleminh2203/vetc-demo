package com.mbbank.vetc.feign.response;

import lombok.Data;

@Data
public class UserFeignResponse {
    private String cif;
    private String fullname;
    private String idCardType;
    private String idCardNo;
    private String dob;
    private String mobile;
    private String email;
    private String sessionId;
    private String masterSessionToken;
    private Long masterSessionExpiredAt;
}
