package com.aceforeverd.kafkaProducer.service;

import com.aceforeverd.kafkaProducer.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public String handleOrder(Order order) {
        kafkaTemplate.send(topic, "hello", order);
        return "OK";
    }
}
