package com.concert.agency.dao;

import com.concert.agency.model.Role;
import java.util.Optional;

public interface RoleDao extends AbstractDao<Role> {
    Optional<Role> getRoleByName(String roleName);
}
