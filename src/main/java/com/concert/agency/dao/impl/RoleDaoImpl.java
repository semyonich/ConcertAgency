package com.concert.agency.dao.impl;

import com.concert.agency.dao.RoleDao;
import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.Role;
import com.concert.agency.model.RoleName;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {
    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Role r WHERE r.name=:name", Role.class)
                    .setParameter("name", RoleName.valueOf(roleName)).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to find Role with name=" + roleName, e);
        }
    }
}
