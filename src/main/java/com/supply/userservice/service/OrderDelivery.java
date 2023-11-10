package com.supply.userservice.service;

import com.supply.userservice.entity.UserOrders;
import com.supply.userservice.repo.UserOrdersRepo;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderDelivery {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDelivery.class);
UserOrderProducerService userOrderProducerService;
UserOrdersRepo userOrdersRepo;
    // Getter for kafkaMessage
    @Getter
    private String kafkaMessage;

    @KafkaListener(topics = "${spring.kafka.topic.order-update-name}", groupId = "${spring.kafka.consumer.group-id.OrderDelivery}")
    public void consumeOrderUpdateTopic(String message) {
        LOGGER.info("{} Congrats !!", message);
        kafkaMessage = message;

//        UserOrders userOrder = new UserOrders();
//        userOrdersRepo.markOrderAsDelivered(userOrder.getUserOrderId());
    }

}
