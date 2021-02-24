package com.concert.agency.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_hall_id")
    private ConcertHall concertHall;
    @Column(name = "showtime")
    private LocalDateTime showTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Performance getMovie() {
        return performance;
    }

    public void setMovie(Performance performance) {
        this.performance = performance;
    }

    public ConcertHall getCinemaHall() {
        return concertHall;
    }

    public void setCinemaHall(ConcertHall concertHall) {
        this.concertHall = concertHall;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "Event{id=" + id + ", movie=" + performance + ", cinemaHall=" + concertHall
                + ", showTime=" + showTime + '}';
    }
}
