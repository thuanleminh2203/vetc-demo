package com.mbbank.vetc.entity;

import com.mbbank.vetc.dto.PHTypeTransactionDTO;
import com.mbbank.vetc.enums.ProcessEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document("transaction")
public class TransactionEntity{
    @Id
    private String id;
    private String idPHTransaction;

    @Indexed
    private String cif;
    private BigDecimal amount;
    private String description;
    private PHTypeTransactionDTO type;
    private LocalDateTime createdTime;
    private LocalDateTime paidTime;
    private String status;
    private String fundingSource;
    private String cardType;
    private String accountNo;
    private ProcessEnum process = ProcessEnum.INIT;

    @CreatedDate
    @Indexed
    private LocalDateTime createdAt;

    @Indexed
    @LastModifiedDate
    private LocalDateTime modifiedAt;


}
