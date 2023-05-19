package com.example.kafaktutorial.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Producer {

    private static final String TOPIC = "test_topic";

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
        log.info(message);
        kafkaTemplate.send(TOPIC,message);
    }
}
