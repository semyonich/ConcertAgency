package com.concert.agency.service.mapper;

import com.concert.agency.model.ShoppingCart;
import com.concert.agency.model.Ticket;
import com.concert.agency.model.dto.ShoppingCartResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

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
