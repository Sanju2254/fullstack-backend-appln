package com.careerit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.careerit.kafka.producer.MessageProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private MessageProducer producer;

    @GetMapping("/send")
    public String send(@RequestParam String msg) {
        producer.sendMessage(msg);
        return "Message sent";
    }
}