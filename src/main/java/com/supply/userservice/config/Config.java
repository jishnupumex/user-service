package com.supply.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;


@Configuration
public class Config {

    @Value("${spring.kafka.topic-json.name}")
    private String userOrderTopicName;

    @Value("${spring.kafka.topic.order-update-name}")
    private String orderUpdateTopicName;


    @Bean
    public NewTopic userOrderTopic() {
        return TopicBuilder.name(userOrderTopicName)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderUpdateTopic() {
        return TopicBuilder.name(orderUpdateTopicName)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
