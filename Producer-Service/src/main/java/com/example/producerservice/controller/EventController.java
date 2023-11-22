package com.example.producerservice.controller;

import com.example.producerservice.models.TransactionMessage;
import com.example.producerservice.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {

    public final KafkaProducerService kafkaProducerService;

    @PostMapping("/event")
    public ResponseEntity<Object> newEvent(@RequestBody TransactionMessage transactionMessage){

        UUID uuid = UUID.randomUUID();

        log.info("UUID {}" , uuid);

        kafkaProducerService.send("transaction-topic" , uuid , transactionMessage);

        return ResponseEntity.ok("Event create successfully.");

    }

}
