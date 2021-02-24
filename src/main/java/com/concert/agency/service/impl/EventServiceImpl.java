package com.concert.agency.service.impl;

import com.concert.agency.dao.EventDao;
import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.Event;
import com.concert.agency.service.EventService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public List<Event> findAvailableEvents(Long eventId, LocalDate date) {
        return eventDao.findAvailableEvents(eventId, date);
    }

    @Override
    public Event add(Event event) {
        return eventDao.add(event);
    }

    @Override
    public void update(Event event) {
        eventDao.update(event);
    }

    @Override
    public void removeById(Long id) {
        eventDao.removeById(id);
    }

    @Override
    public Event get(Long eventId) {
        return eventDao.get(eventId)
                .orElseThrow(() -> new DataProcessingException("Event don't exist, id="
                        + eventId));
    }
}
