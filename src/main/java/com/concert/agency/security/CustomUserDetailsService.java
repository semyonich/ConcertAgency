package com.concert.agency.security;

import com.concert.agency.model.User;
import com.concert.agency.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByEmail(name)
                .orElseThrow(() -> new UsernameNotFoundException("User don't exist"));
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        List<GrantedAuthority> authoritySet = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                .collect(Collectors.toList());
        return userBuilder.password(user.getPassword()).authorities(authoritySet).build();
    }
}
