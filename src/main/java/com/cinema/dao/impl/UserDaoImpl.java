package com.cinema.dao.impl;

import com.cinema.dao.UserDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User u "
                    + "INNER JOIN FETCH u.roles WHERE u.email=:email", User.class)
                    .setParameter("email", email).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to find User with email=" + email, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE id=:id", User.class)
                    .setParameter("id", id).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to find User with id=" + id, e);
        }
    }
}
