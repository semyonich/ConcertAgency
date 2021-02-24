package com.concert.agency.controller;

import com.concert.agency.model.dto.PerformanceRequestDto;
import com.concert.agency.model.dto.PerformanceResponseDto;
import com.concert.agency.service.PerformanceService;
import com.concert.agency.service.mapper.PerformanceMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performances")
public class PerformanceController {
    private final PerformanceService performanceService;
    private final PerformanceMapper performanceMapper;

    public PerformanceController(PerformanceService performanceService,
                                 PerformanceMapper performanceMapper) {
        this.performanceService = performanceService;
        this.performanceMapper = performanceMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody @Valid PerformanceRequestDto performanceRequestDto) {
        performanceService.add(performanceMapper.makeEntity(performanceRequestDto));
    }

    @GetMapping
    public List<PerformanceResponseDto> getAllPerformances() {
        return performanceService.getAll().stream()
                .map(performanceMapper::makeDto)
                .collect(Collectors.toList());
    }
}
