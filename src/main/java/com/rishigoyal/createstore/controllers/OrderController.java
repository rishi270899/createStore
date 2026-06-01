package com.rishigoyal.createstore.controllers;


import com.rishigoyal.createstore.dto.OrderRequest;
import com.rishigoyal.createstore.entities.Order;
import com.rishigoyal.createstore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody @Valid OrderRequest orderRequest) {
       return orderService.createOrder(orderRequest);
    }



}
