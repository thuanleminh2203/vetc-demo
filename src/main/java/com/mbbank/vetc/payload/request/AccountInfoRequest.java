package com.mbbank.vetc.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AccountInfoRequest {
    @ApiModelProperty(notes = "accountNo", example = "This is accountNo")
    @Pattern(regexp = "^E0\\d{1,23}$", message = "accountNo not valid")
    private String accountNo;

    @ApiModelProperty(notes = "plateNumber", example = "This is plateNumber")
    @Pattern(regexp = "^\\d{2}[A-Z]{1,2}\\d{4,5}[V,T,X,D]?$", message = "plateNumber not valid")
    private String plateNumber;
}
