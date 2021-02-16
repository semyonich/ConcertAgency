package com.cinema.service;

import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);

    void update(MovieSession movieSession);

    void removeById(Long id);

    Optional<MovieSession> findById(Long movieSessionId);
}
