package com.mbbank.vetc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("user")
public class UserEntity extends BaseEntity {
    @Id
    private String id;

    @Indexed
    private String cif;

    private String fullname;

    private String dob;

    private String mobile;

    private String email;


}
