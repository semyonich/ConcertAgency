package com.cinema.service.impl;

import com.cinema.dao.RoleDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.Role;
import com.cinema.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName)
                .orElseThrow(() -> new DataProcessingException("Role don't exist, name="
                        + roleName));
    }
}
