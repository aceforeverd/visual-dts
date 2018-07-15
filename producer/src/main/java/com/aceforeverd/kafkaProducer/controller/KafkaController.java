package com.aceforeverd.kafkaProducer.controller;

import com.aceforeverd.kafkaProducer.model.Order;
import com.aceforeverd.kafkaProducer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/kafka")
public class KafkaController {
    @Autowired
    private KafkaService kafkaService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    String handleOrder(@RequestBody Order order) {
        return kafkaService.handleOrder(order);
    }
}
