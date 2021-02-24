package com.concert.agency.dao;

import com.concert.agency.model.Event;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventDao extends AbstractDao<Event> {
    List<Event> findAvailableEvents(Long movieId, LocalDate date);

    void update(Event event);

    void removeById(Long id);

    Optional<Event> get(Long id);
}
