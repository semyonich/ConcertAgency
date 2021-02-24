package com.concert.agency.service.mapper;

import com.concert.agency.model.Order;
import com.concert.agency.model.Ticket;
import com.concert.agency.model.dto.OrderResponseDto;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";

    public OrderResponseDto makeDto(Order order) {
        OrderResponseDto orderDto = new OrderResponseDto();
        orderDto.setId(order.getId());
        orderDto.setUserEmail(order.getUser().getEmail());
        String orderDate = order.getOrderDate()
                .format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        orderDto.setOrderDate(orderDate);
        List<Long> ticketIds = order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        orderDto.setTickets(ticketIds);
        return orderDto;
    }
}
