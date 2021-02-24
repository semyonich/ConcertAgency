package com.concert.agency.service;

import com.concert.agency.model.Performance;
import java.util.List;

public interface PerformanceService {
    Performance add(Performance performance);

    List<Performance> getAll();

    Performance getById(Long id);
}
