package com.cinema.security.impl;

import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.security.AuthenticationService;
import com.cinema.service.RoleService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Role userRole = roleService.getRoleByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
