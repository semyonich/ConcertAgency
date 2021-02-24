package com.concert.agency.service.impl;

import com.concert.agency.dao.RoleDao;
import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.Role;
import com.concert.agency.service.RoleService;
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
