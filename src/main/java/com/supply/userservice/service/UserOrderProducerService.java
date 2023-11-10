package com.supply.userservice.service;

import com.scm.UserOrder;
import com.supply.userservice.entity.OrderStatus;
import com.supply.userservice.entity.UserOrders;
import com.supply.userservice.exceptions.NotFoundException;
import com.supply.userservice.repo.UserOrdersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserOrderProducerService {
    private final UserOrdersRepo userOrdersRepo;
    private final KafkaTemplate<String, UserOrder> kafkaTemplate;

    @Value("UserOrder")
    private String topicJsonName;

    private int currentPartition = 0;

    public UserOrders createOrder(UserOrder data) {
        UserOrders userOrder = new UserOrders();

        userOrder.setOrderStatus(OrderStatus.NEW);
        userOrder.setProdDesc(data.getProdDesc());
        userOrder.setProdId((long) data.getProdId());
        userOrder.setProdName(data.getProdName());
        userOrder.setProdPrice((int) data.getProdPrice());
        userOrder.setProdQty(data.getProdQty());
        userOrder.setProdType(data.getProdType());
        userOrder.setTotalPrice((int) (data.getProdQty()* data.getProdPrice()));
        userOrder.setUserId((long) data.getUserId());

        // Save the UserOrder to the database
        UserOrders savedUserOrder = userOrdersRepo.save(userOrder);
//        userOrdersRepo.save(userOrder);
        // Send the UserOrder to the Kafka topic
        log.info(String.format("Order sent -> %s", userOrder));

        Message<UserOrders> message = MessageBuilder
                .withPayload(savedUserOrder)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .setHeader(KafkaHeaders.PARTITION, currentPartition)
                .build();

        // Update currentPartition for the next message
        currentPartition = (currentPartition + 1) % 3;  // 3 is the number of partitions (0, 1, 2)
        kafkaTemplate.send(message);
        return savedUserOrder; // Return the savedUserOrder
    }

    public Long generateRandomOrderNumber() {

        long timestamp = System.currentTimeMillis();
        int randomValue = (int) (Math.random() * 1000);

        String orderNumberString = timestamp + "" + randomValue;
        return Long.parseLong(orderNumberString);
    }

    public List<UserOrders> findUserAllOrder() {
        return userOrdersRepo.findAll();
    }

    public UserOrders findUserOrderById(Long orderId) throws NotFoundException {
        return userOrdersRepo.findById(orderId).orElseThrow(() -> new NotFoundException("Record not found"));

    }
}
