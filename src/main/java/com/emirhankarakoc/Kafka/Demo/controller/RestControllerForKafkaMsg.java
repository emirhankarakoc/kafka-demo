package com.emirhankarakoc.Kafka.Demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emirhankarakoc.Kafka.Demo.service.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


    @RestController
    @RequestMapping("/rest/api")
    public class RestControllerForKafkaMsg {

        @Autowired
        Producer producer;


        @GetMapping("/producerMsg")
    public String getMessageFromClient(@RequestParam String message) {
        System.out.println("HTTP -> " + message);
        producer.sendMsgToTopic(message);
        return "OK";
    }



    
    }
