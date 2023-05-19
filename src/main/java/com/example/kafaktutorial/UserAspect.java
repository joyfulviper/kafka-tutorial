package com.example.kafaktutorial;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect {

    private final KafkaTemplate<String ,Object> kafkaTemplate;

    @AfterReturning(pointcut = "execution(* com.example.kafaktutorial.UserService.createUser(..))", returning = "user")
    public void sendToKafka(User user) {
        System.out.println("AOP 작동");

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("UserCreatedTopic", user);

        future.whenComplete((result, exception) -> {
            if (exception != null) {
                System.out.println("예외 발생 Dead Letter Queue에 메시지 전송");
                kafkaTemplate.send("DLQ", user);
            } else {
                System.out.println("메시지 큐에 전송 성공");
            }
        });
    }

}
