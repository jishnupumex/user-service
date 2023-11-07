package com.supply.userservice.service;

import com.scm.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserOrderProducerService {

    @Value("UserOrder")
    private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserOrderProducerService.class);

    private KafkaTemplate<String, UserOrder> kafkaTemplate;
    public UserOrderProducerService(KafkaTemplate<String, UserOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendOrder(UserOrder data){

        LOGGER.info(String.format("Order sent -> %s", data.toString()));

        Message<UserOrder> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .setHeader(KafkaHeaders.PARTITION, 1)
                .build();

        kafkaTemplate.send(message);
    }
}
