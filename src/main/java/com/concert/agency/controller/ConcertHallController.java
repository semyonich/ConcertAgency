package com.concert.agency.controller;

import com.concert.agency.model.dto.ConcertHallRequestDto;
import com.concert.agency.model.dto.ConcertHallResponseDto;
import com.concert.agency.service.ConcertHallService;
import com.concert.agency.service.mapper.ConcertHallMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert-halls")
public class ConcertHallController {
    private final ConcertHallService concertHallService;
    private final ConcertHallMapper concertHallMapper;

    public ConcertHallController(ConcertHallService concertHallService,
                                 ConcertHallMapper concertHallMapper) {
        this.concertHallService = concertHallService;
        this.concertHallMapper = concertHallMapper;
    }

    @PostMapping
    public void addConcertHall(@RequestBody @Valid ConcertHallRequestDto concertHallRequestDto) {
        concertHallService.add(concertHallMapper.makeEntity(concertHallRequestDto));
    }

    @GetMapping
    public List<ConcertHallResponseDto> getAllConcertHalls(Authentication auth) {
        return concertHallService.getAll().stream()
                .map(concertHallMapper::makeDto)
                .collect(Collectors.toList());
    }
}
