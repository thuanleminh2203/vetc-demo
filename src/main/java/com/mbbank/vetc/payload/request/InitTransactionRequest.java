package com.mbbank.vetc.payload.request;

import com.mbbank.vetc.annotation.ContainStrings;
import com.mbbank.vetc.annotation.StringValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class InitTransactionRequest {

    @NotBlank(message = "amount is required")
    @StringValue(acceptedValues = {"100000", "200000", "300000", "500000", "1000000", "2000000"}, message = "Amount is not accept")
//    @StringValue(acceptedValues = {"PAYMENT_ACCOUNT","CARD","AUTO_DEBT_EXTRACTION","BANNER","FEATURED_APP","PUSH_NOTIFICATION"}, message = "otherServices not valid")
    @Size(max = 7, message = "amount must be less than 7 characters")
    @ApiModelProperty(notes = "amount", example = "100000", required = true)
    private String amount;

    @ApiModelProperty(notes = "accountNo", example = "This is accountNo")
    @Pattern(regexp = "^E0\\d{1,23}$", message = "accountNo not valid")
    @NotBlank(message = "accountNo is required")
    private String accountNo;

}
