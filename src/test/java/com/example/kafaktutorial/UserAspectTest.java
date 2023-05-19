package com.example.kafaktutorial;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserAspectTest {

    @MockBean
    private KafkaTemplate<String ,User> kafkaTemplate;

    @Autowired
    private UserService userService;

    @Test
    void test() {
        doThrow(new RuntimeException()).when(kafkaTemplate).send(anyString(), any());

        assertThrows(
                RuntimeException.class,
                () -> userService.createUser(new User("1", "test", "testEmail"))
        );

        verify(kafkaTemplate, times(4)).send(anyString(), any(User.class));
    }
}