package com.example.producerservice.service;

import com.example.producerservice.models.TransactionMessage;

import org.apache.kafka.clients.GroupRebalanceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.streams.messaging.MessagingFunction;
import org.springframework.kafka.streams.messaging.MessagingProcessor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class KafkaProducerService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);


    @Autowired
    public KafkaTemplate<UUID , TransactionMessage> kafkaTemplate;

    // method for send topic
    public void send (String topicName , UUID key , TransactionMessage transactionMessage){

        var future = kafkaTemplate.send(topicName , key , transactionMessage);

        future.whenComplete((sendResult , exception) -> {

            if (exception != null){

                LOGGER.error(exception.getMessage());

                future.completeExceptionally(exception);

            } else {

                LOGGER.info("The id is : " + transactionMessage.getTransactionId()

                        + "Transaction status to Kafka Topic " + transactionMessage.getStatus());
            }
        });

    }

}
