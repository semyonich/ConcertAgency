package com.concert.agency.service;

import com.concert.agency.model.Event;
import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<Event> findAvailableEvents(Long eventId, LocalDate date);

    Event add(Event event);

    void update(Event event);

    void removeById(Long id);

    Event get(Long eventId);
}
