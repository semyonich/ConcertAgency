package com.cinema.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieSessionRequestDto {
    @NotNull(message = "MovieId cant be null")
    @Min(value = 1, message = "movieId must be greater than 0")
    private Long movieId;
    @NotNull(message = "CinemaHallId cant be null")
    @Min(value = 1, message = "cinemaHallId must be greater than 0")
    private Long cinemaHallId;
    @NotNull(message = "ShowTime cant be null")
    @Size(min = 16, max = 16, message = "ShowTime format:dd.MM.yyyy HH:mm")
    private String showTime;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
