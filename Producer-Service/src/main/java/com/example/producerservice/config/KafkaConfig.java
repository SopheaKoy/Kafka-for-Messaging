package com.example.producerservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {

    // create Bean topic
    @Bean
    public NewTopic topic(){
        return TopicBuilder
                .name("transaction-topic")
                .partitions(2)
                .replicas(1)
                .build();
    }


}
