package com.supply.userservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Config {

    @Value("UserOrder")
    private String topicJsonName;

    @Bean
    public NewTopic JsonTopic(){
        return TopicBuilder.name(topicJsonName)
                .build();
    }

    @Value("${spring.kafka.topic.order-update-name}")
    private String topicOrderUpdateName;

    @Bean
    public NewTopic OrderUpdateTopic() {
        return TopicBuilder.name(topicOrderUpdateName)
                .build();
    }
}
