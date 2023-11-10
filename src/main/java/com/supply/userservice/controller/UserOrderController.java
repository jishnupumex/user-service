package com.supply.userservice.controller;

import com.supply.userservice.entity.OrderResponse;
import com.supply.userservice.entity.UserOrders;
import com.supply.userservice.exceptions.NotFoundException;
import com.supply.userservice.service.OrderDelivery;
import com.supply.userservice.service.UserOrderProducerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scm.UserOrder;
import java.util.List;

@RestController
@RequestMapping("/restapi/orders")
@CrossOrigin(origins = "*")
public class UserOrderController {
    private final UserOrderProducerService userOrderService;
    private final OrderDelivery orderDelivery;

    public UserOrderController(UserOrderProducerService userOrder, OrderDelivery orderDelivery) {
        this.userOrderService = userOrder;
        this.orderDelivery = orderDelivery;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> publish(@RequestBody UserOrder userOrder) {
//            userOrderService.createOrder(userOrder);
            UserOrders savedUserOrder = userOrderService.createOrder(userOrder);
            String message = "Order sent";
            OrderResponse response = new OrderResponse(savedUserOrder, message);
            return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserOrders>> getAllOrders() {
        List<UserOrders> ordersList = userOrderService.findUserAllOrder();
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<UserOrders> getOrderById(@PathVariable Long orderId) throws NotFoundException {

        return ResponseEntity.ok(userOrderService.findUserOrderById(orderId));
    }
}
