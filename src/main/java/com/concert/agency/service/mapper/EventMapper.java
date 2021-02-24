package com.concert.agency.service.mapper;

import com.concert.agency.model.Event;
import com.concert.agency.model.dto.EventRequestDto;
import com.concert.agency.model.dto.EventResponseDto;
import com.concert.agency.service.ConcertHallService;
import com.concert.agency.service.PerformanceService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";
    private final PerformanceService performanceService;
    private final ConcertHallService concertHallService;

    public EventMapper(PerformanceService performanceService,
                       ConcertHallService concertHallService) {
        this.performanceService = performanceService;
        this.concertHallService = concertHallService;
    }

    public EventResponseDto makeDto(Event event) {
        EventResponseDto eventResponseDto = new EventResponseDto();
        eventResponseDto.setId(event.getId());
        eventResponseDto.setPerformance(event.getMovie().getTitle());
        eventResponseDto.setConcertHall(event.getCinemaHall().getDescription());
        String showTime = event.getShowTime()
                .format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        eventResponseDto.setShowTime(showTime);
        return eventResponseDto;
    }

    public Event makeEntity(EventRequestDto eventRequestDto) {
        Event event = new Event();
        event.setMovie(performanceService.getById(eventRequestDto.getPerformanceId()));
        event.setCinemaHall(concertHallService.getById(eventRequestDto
                .getConcertHallId()));
        LocalDateTime dateTime = LocalDateTime.parse(eventRequestDto.getShowTime(),
                DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        event.setShowTime(dateTime);
        return event;
    }
}
