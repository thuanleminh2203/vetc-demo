package com.mbbank.vetc.repository;

import com.mbbank.vetc.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String> {

    Optional<UserEntity> findByCif(String cif);
}
