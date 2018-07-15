package com.aceforeverd.kafkaProducer.model;

import lombok.*;

@Builder
@Data
public class Order {
    private String src;

    private String dst;

    private double value;

    private String time;
}
