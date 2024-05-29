package io.vehita.service;

import io.jsonwebtoken.Claims;
import io.vehita.domain.dto.AuthResponseData;
import io.vehita.domain.dto.SilentRefreshResponseData;
import io.vehita.domain.dto.UserHttpRequestData;
import io.vehita.domain.entity.Role;
import io.vehita.domain.entity.RoleType;
import io.vehita.domain.entity.User;
import io.vehita.exception.DuplicatedUsernameException;
import io.vehita.exception.EmptyCookieException;
import io.vehita.exception.InvalidPasswordException;
import io.vehita.exception.UserNotFoundException;
import io.vehita.repository.RoleRepository;
import io.vehita.repository.UserRepository;
import io.vehita.util.CookieUtil;
import io.vehita.util.JwtUtil;
import io.vehita.util.TokenType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    public AuthResponseData login(UserHttpRequestData request, HttpServletResponse response) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        if (!isValidPassword(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        AuthResponseData responseData = modelMapper.map(user, AuthResponseData.class);
        tokenDispenser(jwtUtil, responseData);

        response.addCookie(cookieUtil.createCookieWithToken(responseData.getAccessToken(), TokenType.ACCESS_TOKEN));
        response.addCookie(cookieUtil.createCookieWithToken(responseData.getRefreshToken(), TokenType.REFRESH_TOKEN));

        return responseData;
    }

    public AuthResponseData register(UserHttpRequestData request) {
        User user = new User(request);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicatedUsernameException(user.getUsername());
        }
        user = userRepository.save(user);
        AuthResponseData responseData = modelMapper.map(user, AuthResponseData.class);
        tokenDispenser(jwtUtil, responseData);

        roleRepository.save(new Role(user.getUsername(), RoleType.USER));

        return responseData;
    }

    public SilentRefreshResponseData silentRefresh(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            throw new EmptyCookieException();
        }

        String refreshToken = cookieUtil.parseTokenFromCookies(
                request.getCookies(),
                TokenType.REFRESH_TOKEN
        );

        Claims claims = jwtUtil.parseToken(refreshToken);
        String username = claims.get("username", String.class);

        String newAccessToken = jwtUtil.generateToken(username, TokenType.ACCESS_TOKEN);

        Cookie newToken = cookieUtil.createCookieWithToken(newAccessToken, TokenType.ACCESS_TOKEN);
        response.addCookie(newToken);
        System.out.println(username);
        User source = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return modelMapper.map(source, SilentRefreshResponseData.class);
    }

    public HttpHeaders clearAllCookies() {
        HttpHeaders headers = new HttpHeaders();

        headers.add("set-cookie", "access_token=null; max-age=0");
        headers.add("set-cookie", "refresh_token=null; max-age=0");

        return headers;
    }

    public String parseUsernameFromCookies(Cookie[] cookies) {
        String accessToken = cookieUtil.parseTokenFromCookies(cookies, TokenType.ACCESS_TOKEN);
        Claims claims = jwtUtil.parseToken(accessToken);

        return claims.get("username", String.class);
    }

    public List<Role> getRoles(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotFoundException(username);
        }

        return roleRepository.findAllByUsername(username);
    }

    private static void tokenDispenser(JwtUtil jwtUtil, AuthResponseData data) {
        String accessToken = jwtUtil.generateToken(data.getUsername(), TokenType.ACCESS_TOKEN);
        String refreshToken = jwtUtil.generateToken(data.getUsername(), TokenType.REFRESH_TOKEN);

        data.complete(accessToken, refreshToken);
    }

    private boolean isValidPassword(String password, String valid) {
        return valid.equals(password);
    }
}
