package com.supply.userservice.controller;

import com.supply.userservice.service.UserOrderProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scm.UserOrder;
@RestController
@RequestMapping("/restapi")
public class UserOrderController {

    private final UserOrderProducerService userOrderService;

    public UserOrderController(UserOrderProducerService userOrder) {
        this.userOrderService = userOrder;
    }

    @PostMapping("/order")
    public ResponseEntity<String> publish(@RequestBody UserOrder userOrder){
        userOrderService.sendOrder(userOrder);
        return ResponseEntity.ok("User Order sent to Kafka");
    }
}
