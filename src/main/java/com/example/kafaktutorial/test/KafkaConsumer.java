package com.example.kafaktutorial.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test_topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}