package com.example.kafaktutorial;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreatedEventListener {
    private final UserQueryRepository userQueryRepository;

    @KafkaListener(topics = "UserCreatedTopic", groupId = "my-group")
    public void onUserCreated(User user) {
        System.out.println("UserCreate 메시지 받음");
        userQueryRepository.add(user);
    }
}