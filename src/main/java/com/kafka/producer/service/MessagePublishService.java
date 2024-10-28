package com.kafka.producer.service;

import com.kafka.producer.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessagePublishService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("javatechie-custom-topic", message);
//        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("some-new-topic", message);
//        if the topic doesn't exist already spring creates it with default config ---- partition count = 1, replication-factor = 1
//        to be able to set custom properties we can create a bean in cofig file
        future.whenComplete((result, ex) -> {
           if(ex == null) {
               System.out.println("message sent, offset : " + result.getRecordMetadata().offset());
//               result.getRecordMetadata().partition();
           } else {
               System.out.println("error while sending the message : " + ex);
           }
        });
    }

    public void sendEmployee(Employee employee) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("javatechie-custom-topic", employee);
        future.whenComplete((result, ex) -> {
            if(ex == null) {
                System.out.println("employee sent " + employee.toString() + ", offset : " + result.getRecordMetadata().offset());
            } else {
                System.out.println("error while sending the employee : " + ex);
            }
        });
    }
}
