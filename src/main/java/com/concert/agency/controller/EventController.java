package com.concert.agency.controller;

import com.concert.agency.model.Event;
import com.concert.agency.model.dto.EventRequestDto;
import com.concert.agency.model.dto.EventResponseDto;
import com.concert.agency.service.EventService;
import com.concert.agency.service.mapper.EventMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {
    private static final String AVAILABLE_SESSIONS_DATE_FORMAT = "dd.MM.yyyy";
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService,
                           EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    public void addEvent(@RequestBody @Valid EventRequestDto eventRequestDto) {
        eventService.add(eventMapper.makeEntity(eventRequestDto));
    }

    @GetMapping("/available")
    public List<EventResponseDto> getAvailableEvents(@RequestParam Long performanceId,
                                                       @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter
                .ofPattern(AVAILABLE_SESSIONS_DATE_FORMAT));
        return eventService.findAvailableEvents(performanceId, localDate).stream()
                .map(eventMapper::makeDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void updateEvent(@RequestBody EventRequestDto eventRequestDto,
                                   @PathVariable Long id) {
        Event event = eventMapper.makeEntity(eventRequestDto);
        event.setId(id);
        eventService.update(event);
    }

    @DeleteMapping("/{id}")
    public void removeEvent(@PathVariable Long id) {
        eventService.removeById(id);
    }
}
