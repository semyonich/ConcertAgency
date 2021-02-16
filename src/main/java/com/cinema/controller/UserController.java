package com.cinema.controller;

import com.cinema.exception.DataProcessingException;
import com.cinema.model.User;
import com.cinema.model.dto.UserResponseDto;
import com.cinema.service.UserService;
import com.cinema.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto findUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new DataProcessingException("User don't exist"));
        return userMapper.makeDto(user);
    }
}
