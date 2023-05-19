package com.example.kafaktutorial.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {


    private final Producer producer;

    @PostMapping("/publish")
    public void messageToTopic(@RequestParam("message") String message){

        producer.sendMessage(message);

    }
}
