package com.concert.agency.util;

import com.concert.agency.model.Role;
import com.concert.agency.model.RoleName;
import com.concert.agency.model.User;
import com.concert.agency.service.RoleService;
import com.concert.agency.service.ShoppingCartService;
import com.concert.agency.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InitialInject {
    private static final String ADMIN_EMAIL = "serhii@gmail.com";
    private static final String ADMIN_PWD = "1234";
    private final RoleService roleService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public InitialInject(RoleService roleService, UserService userService,
                         ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    public void inject() {
        for (RoleName item : RoleName.values()) {
            Role role = new Role();
            role.setName(item);
            roleService.add(role);
        }
        User admin = new User();
        admin.setEmail(ADMIN_EMAIL);
        admin.setPassword(ADMIN_PWD);
        Role adminRole = roleService.getRoleByName(RoleName.ADMIN.name());
        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(adminRole);
        admin.setRoles(adminRoles);
        admin = userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
    }
}
