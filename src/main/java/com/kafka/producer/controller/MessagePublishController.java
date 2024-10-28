package com.kafka.producer.controller;

import com.kafka.producer.dto.Employee;
import com.kafka.producer.service.MessagePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class MessagePublishController {
    @Autowired
    private MessagePublishService messagePublishService;

    @PostMapping("/sendMessage")
    public ResponseEntity<String> publishMessage(@RequestParam String message) {
        for(int i = 0 ; i < 5000; i++) {
            messagePublishService.sendMessage(message);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendEmployee")
    public ResponseEntity<String> publishEmployee(@RequestBody Employee employee) {
        messagePublishService.sendEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
