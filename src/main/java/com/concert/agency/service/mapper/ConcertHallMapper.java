package com.concert.agency.service.mapper;

import com.concert.agency.model.ConcertHall;
import com.concert.agency.model.dto.ConcertHallRequestDto;
import com.concert.agency.model.dto.ConcertHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ConcertHallMapper {
    public ConcertHallResponseDto makeDto(ConcertHall concertHall) {
        ConcertHallResponseDto concertHallResponseDto = new ConcertHallResponseDto();
        concertHallResponseDto.setId(concertHall.getId());
        concertHallResponseDto.setCapacity(concertHall.getCapacity());
        concertHallResponseDto.setDescription(concertHall.getDescription());
        return concertHallResponseDto;
    }

    public ConcertHall makeEntity(ConcertHallRequestDto concertHallRequestDto) {
        ConcertHall concertHall = new ConcertHall();
        concertHall.setCapacity(concertHallRequestDto.getCapacity());
        concertHall.setDescription(concertHallRequestDto.getDescription());
        return concertHall;
    }
}
