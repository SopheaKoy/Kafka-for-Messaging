package com.example.consumerservice.config;

import com.example.consumerservice.models.TransactionMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    // create consumer factory
    @Bean
    public ConsumerFactory<UUID, TransactionMessage> consumerFactory(){

        Map<String , Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG , "group-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , JsonDeserializer.class);

        // handle error
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, UUIDDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS , JsonDeserializer.class);
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS , false);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE , "com.example.consumerservice.models.TransactionMessage");

        return new DefaultKafkaConsumerFactory<>(props);

    }


    // create concurrentMessageListenerContainer

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID , TransactionMessage> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<UUID , TransactionMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

}
