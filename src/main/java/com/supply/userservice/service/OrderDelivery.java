package com.supply.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderDelivery {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDelivery.class);

    @KafkaListener(topics = "${spring.kafka.topic.order-update-name}", groupId = "${spring.kafka.consumer.group-id.OrderDelivery}")
    public void consumeOrderUpdateTopic(String message) {
        LOGGER.info("{} Congrats !!", message);
    }
}
