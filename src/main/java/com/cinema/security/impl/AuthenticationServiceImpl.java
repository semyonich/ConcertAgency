package com.cinema.security.impl;

import com.cinema.exception.AuthenticationException;
import com.cinema.lib.Inject;
import com.cinema.lib.Service;
import com.cinema.model.User;
import com.cinema.security.AuthenticationService;
import com.cinema.service.UserService;
import com.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Login or password incorrect"));
        if (user.getPassword().equals(HashUtil.getHashedPassword(password, user.getSalt()))) {
            return user;
        }
        throw new AuthenticationException("Login or password incorrect");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.add(user);
    }
}
