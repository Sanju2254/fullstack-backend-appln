package com.careerit.kafka.dto;

import java.io.Serializable;

public class KafkaMessage implements Serializable {

    private String message;

    public KafkaMessage() {
    }

    public KafkaMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "KafkaMessage{message='" + message + "'}";
    }
}