package com.concert.agency.controller;

import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.User;
import com.concert.agency.model.dto.UserResponseDto;
import com.concert.agency.service.UserService;
import com.concert.agency.service.mapper.UserMapper;
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
