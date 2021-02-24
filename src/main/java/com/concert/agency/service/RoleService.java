package com.concert.agency.service;

import com.concert.agency.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
