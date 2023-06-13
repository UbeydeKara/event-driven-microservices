package com.ubeydekara.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderEmailTopic() {
        return TopicBuilder.name("order-email")
                .build();
    }

    @Bean
    public NewTopic orderStockTopic() {
        return TopicBuilder.name("order-stock")
                .build();
    }
}
