package com.example.consumerservice.service;

import com.example.consumerservice.models.TransactionMessage;
import com.example.consumerservice.repository.KafkaConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final KafkaConsumerRepository consumerRepository;

    @KafkaListener(topics = {"transaction-topic"} , groupId = "group-id")
    public void consumer(TransactionMessage transactionMessage){

        log.info(" We received the transaction with the id  " + transactionMessage.getTransactionId() +

                "  The status is  " + transactionMessage.getStatus()
        );

        consumerRepository.save(transactionMessage);

    }

}
