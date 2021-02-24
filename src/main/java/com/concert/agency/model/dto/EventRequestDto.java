package com.concert.agency.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventRequestDto {
    @NotNull(message = "PerformanceId cant be null")
    @Min(value = 1, message = "PerformanceId must be greater than 0")
    private Long performanceId;
    @NotNull(message = "ConcertHallId cant be null")
    @Min(value = 1, message = "ConcertHallId must be greater than 0")
    private Long concertHallId;
    @NotNull(message = "ShowTime cant be null")
    @Size(min = 16, max = 16, message = "ShowTime format:dd.MM.yyyy HH:mm")
    private String showTime;

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long eventId) {
        this.performanceId = eventId;
    }

    public Long getConcertHallId() {
        return concertHallId;
    }

    public void setConcertHallId(Long concertHallId) {
        this.concertHallId = concertHallId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
