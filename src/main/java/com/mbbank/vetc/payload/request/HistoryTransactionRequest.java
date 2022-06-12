package com.mbbank.vetc.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class HistoryTransactionRequest {

    @NotNull(message = "startDate is required")
    @ApiModelProperty(notes = "Day start display banner", example = "yyyy-MM-dd", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @NotNull(message = "endDate is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty(notes = "Day end display banner", example = "yyyy-MM-dd", required = true)
    private LocalDate endDate;

    private long pageIndex = 1;
    private long pageSize = 10;
}
