package com.mbbank.vetc.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mbbank.vetc.constant.AppConstants;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OauthResponse {
    private String token;
    private final String tokenType = AppConstants.BEARER_TOKEN_TYPE;

}
