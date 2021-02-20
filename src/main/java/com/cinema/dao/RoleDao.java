package com.cinema.dao;

import com.cinema.model.Role;
import java.util.Optional;

public interface RoleDao extends AbstractDao<Role> {
    Optional<Role> getRoleByName(String roleName);
}
