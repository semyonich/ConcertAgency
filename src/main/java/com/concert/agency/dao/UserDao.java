package com.concert.agency.dao;

import com.concert.agency.model.User;
import java.util.Optional;

public interface UserDao extends AbstractDao<User> {
    Optional<User> findByEmail(String email);

    Optional<User> get(Long id);
}
