package io.vehita.filter;

import io.vehita.exception.EmptyCookieException;
import io.vehita.exception.InvalidTokenException;
import io.vehita.exception.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class AuthenticationErrorFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        
        try {
            chain.doFilter(request, response);
        } catch (InvalidTokenException | EmptyCookieException | TokenExpiredException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
