package com.concert.agency.service.mapper;

import com.concert.agency.model.Performance;
import com.concert.agency.model.dto.PerformanceRequestDto;
import com.concert.agency.model.dto.PerformanceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PerformanceMapper {
    public PerformanceResponseDto makeDto(Performance performance) {
        PerformanceResponseDto performanceResponseDto = new PerformanceResponseDto();
        performanceResponseDto.setId(performance.getId());
        performanceResponseDto.setTitle(performance.getTitle());
        performanceResponseDto.setDescription(performance.getDescription());
        return performanceResponseDto;
    }

    public Performance makeEntity(PerformanceRequestDto performanceRequestDto) {
        Performance performance = new Performance();
        performance.setTitle(performanceRequestDto.getTitle());
        performance.setDescription(performanceRequestDto.getDescription());
        return performance;
    }

}
