package com.ubeydekara.kafka.service;

public interface ProducerService {
    void sendMessage(String topic, Object message);
}
