package com.cinema.controller;

import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;
import com.cinema.service.MovieSessionService;
import com.cinema.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private static final String AVAILABLE_SESSIONS_DATE_FORMAT = "dd.MM.yyyy";
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.makeEntity(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSessions(@RequestParam Long movieId,
                                                              @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter
                .ofPattern(AVAILABLE_SESSIONS_DATE_FORMAT));
        return movieSessionService.findAvailableSessions(movieId, localDate).stream()
                .map(movieSessionMapper::makeDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public void updateMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.update(movieSessionMapper.makeEntity(movieSessionRequestDto));
    }

    @DeleteMapping
    public void removeMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.remove(movieSessionMapper.makeEntity(movieSessionRequestDto));
    }
}
