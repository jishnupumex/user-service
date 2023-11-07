package com.supply.userservice.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @Value("${spring.kafka.topic-json.name}")
    private String userOrderTopicName;

    @Value("${spring.kafka.topic.order-update-name}")
    private String orderUpdateTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Set your broker address here
        return new KafkaAdmin(configs);
    }

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
