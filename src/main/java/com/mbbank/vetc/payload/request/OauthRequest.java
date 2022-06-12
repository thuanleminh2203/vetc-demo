package com.mbbank.vetc.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OauthRequest {

    @NotBlank(message = "token is required")
    @Size(max = 255,message = "token must be less than 255 characters")
    @ApiModelProperty(notes = "token", example = "MB-kasfas-asdasv-asfqwf", required = true)
    private String token;
}
