package com.concert.agency.security;

import com.concert.agency.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
