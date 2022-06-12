package com.mbbank.vetc.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private int status;
    private String error;
    private String errorCode;
    private String message;
    private Map<String, String> validationMessage;

    public ExceptionMessage(Date timestamp, int status, String error, String message) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ExceptionMessage(Date timestamp, int status, String error, String message,Map<String, String> validationMessage) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.validationMessage=validationMessage;
    }

    public ExceptionMessage(Date timestamp, int status, String error, String message,Map<String, String> validationMessage, String errorCode) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.validationMessage=validationMessage;
        this.errorCode = errorCode;
    }
}
