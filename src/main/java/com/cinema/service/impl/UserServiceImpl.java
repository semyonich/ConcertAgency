package com.cinema.service.impl;

import com.cinema.dao.UserDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.User;
import com.cinema.service.UserService;
import com.cinema.util.HashUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.getHashedPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId)
                .orElseThrow(() -> new DataProcessingException("User don't exist, id=" + userId));
    }
}
