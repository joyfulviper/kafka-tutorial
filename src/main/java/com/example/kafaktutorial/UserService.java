package com.example.kafaktutorial;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;

    public User createUser(User user) {
        userCommandRepository.add(user);
        return user;
    }

    public List<User> findAll() {
        return userQueryRepository.findAll();
    }
}