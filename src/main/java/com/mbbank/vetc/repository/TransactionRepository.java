package com.mbbank.vetc.repository;

import com.mbbank.vetc.entity.TransactionEntity;
import com.mbbank.vetc.enums.ProcessEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity,String> {
    Optional<TransactionEntity> findByIdPHTransaction(String idPHTransaction);
    List<TransactionEntity> findAllByProcess(ProcessEnum process);
    List<TransactionEntity> findAllByProcessAndCreatedAtGreaterThanEqual(ProcessEnum process, LocalDateTime createdAt);
    List<TransactionEntity> findAllByProcessInAndCreatedAtLessThanEqual(List<ProcessEnum> processes, LocalDateTime createdAt);


}
