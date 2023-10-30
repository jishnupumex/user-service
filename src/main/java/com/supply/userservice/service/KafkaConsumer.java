//package com.supply.userservice.service;
//
//import com.scm.UserOrder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumer {
//    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
//
//
//    @KafkaListener(topics = "UserOrder", groupId = "Orders")
//    public void consume(UserOrder order) {
//        LOGGER.info(String.format("Order received -> %s", order.toString()));
//    }
//
//}