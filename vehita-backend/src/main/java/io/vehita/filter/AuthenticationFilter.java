package io.vehita.filter;

import io.vehita.domain.entity.Role;
import io.vehita.security.CustomUserAuthentication;
import io.vehita.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;

public class AuthenticationFilter extends BasicAuthenticationFilter {
    private final AuthenticationService authenticationService;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                AuthenticationService authenticationService) {
        super(authenticationManager);
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        if (request.getCookies() != null) {
            String username = authenticationService.parseUsernameFromCookies(request.getCookies());
            List<Role> roles = authenticationService.getRoles(username);

            Authentication customUserAuthentication = new CustomUserAuthentication(
                    username,
                    roles
            );

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(customUserAuthentication);
        }

        chain.doFilter(request, response);
    }
}
