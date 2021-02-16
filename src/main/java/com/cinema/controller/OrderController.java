package com.cinema.controller;

import com.cinema.model.dto.OrderResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("/orders")
public class OrderController {

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {

    }

    @GetMapping
    public List<OrderResponseDto> getAllOrdersByUserId(@RequestParam Long userId) {
        return null;
    }
}
