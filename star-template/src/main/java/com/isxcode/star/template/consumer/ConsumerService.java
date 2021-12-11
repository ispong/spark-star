package com.isxcode.star.template.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(id = "test-consumer-group", topics = "ispong-input-1")
    public void listenKafka(String content) {

        System.out.println("receive:" + content);
    }
}
