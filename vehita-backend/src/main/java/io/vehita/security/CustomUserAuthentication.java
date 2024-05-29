package io.vehita.security;

import io.vehita.domain.entity.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class CustomUserAuthentication extends AbstractAuthenticationToken {

    private final String username;

    public CustomUserAuthentication(String username, List<Role> roles) {
        super(authorities(roles));
        this.username = username;
    }

    private static List<? extends GrantedAuthority> authorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
