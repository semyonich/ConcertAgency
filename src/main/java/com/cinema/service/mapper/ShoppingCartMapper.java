package com.cinema.service.mapper;

import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.dto.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto makeDto(ShoppingCart cart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(cart.getId());
        List<Long> ticketIds = cart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        dto.setTickets(ticketIds);
        return dto;
    }
}
