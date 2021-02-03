package com.cinema.dao;

import com.cinema.model.User;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByEmail(String email);
}
