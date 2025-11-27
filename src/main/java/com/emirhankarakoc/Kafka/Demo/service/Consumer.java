package com.emirhankarakoc.Kafka.Demo.service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service

public class Consumer {

    @KafkaListener(topics = "messageTopic",groupId = "messageService")
    public void listenToTopic(String receivedMessage){
        System.out.println("The message received :"+ receivedMessage);
    }
    
}
