package com.concert.agency.controller;

import com.concert.agency.model.dto.UserRequestDto;
import com.concert.agency.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRequestDto userDto) {
        authService.register(userDto.getEmail(), userDto.getPassword());
    }
}
