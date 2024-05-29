package io.vehita.config;

import io.vehita.filter.AuthenticationErrorFilter;
import io.vehita.filter.AuthenticationFilter;
import io.vehita.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationService authenticationService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, authenticationService);
        AuthenticationErrorFilter authenticationErrorFilter = new AuthenticationErrorFilter();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(HeadersConfigurer::disable);
        http
                .addFilter(authenticationFilter)
                .addFilterBefore(authenticationErrorFilter, AuthenticationFilter.class);
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/users/sign-up").permitAll()
                                .requestMatchers("/users/sign-in").permitAll()
                                .requestMatchers("/users/exists/*").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                );
        http
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
