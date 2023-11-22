package com.example.consumerservice.repository;

import com.example.consumerservice.models.TransactionMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaConsumerRepository extends JpaRepository<TransactionMessage , Long> {
}
