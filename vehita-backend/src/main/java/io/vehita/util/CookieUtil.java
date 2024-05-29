package io.vehita.util;

import io.vehita.exception.EmptyCookieException;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    private final int ONE_DAY = 1000 * 60 * 60 * 24;
    private final int TWO_WEEK = 1000 * 60 * 60 * 24 * 14;

    public String parseTokenFromCookies(Cookie[] cookies, TokenType tokenType) {
        String cookieName;

        if (tokenType.equals(TokenType.ACCESS_TOKEN)) {
            cookieName = String.valueOf(TokenType.ACCESS_TOKEN).toLowerCase();
        } else if (tokenType.equals(TokenType.REFRESH_TOKEN)) {
            cookieName = String.valueOf(TokenType.REFRESH_TOKEN).toLowerCase();
        } else {
            cookieName = "empty";
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }

        throw new EmptyCookieException();
    }

    public Cookie createCookieWithToken(String token, TokenType tokenType) {
        Cookie cookie;

        if (tokenType.equals(TokenType.ACCESS_TOKEN)) {
            cookie = new Cookie(String.valueOf(TokenType.ACCESS_TOKEN).toLowerCase(), token);
            cookie.setMaxAge(ONE_DAY);
        } else {
            cookie = new Cookie(String.valueOf(TokenType.REFRESH_TOKEN).toLowerCase(), token);
            cookie.setMaxAge(TWO_WEEK);
        }

        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    public void expiredTokenFromCookies(Cookie[] cookies, TokenType tokenType) {
        String cookieName = "empty";

        if (tokenType.equals(TokenType.ACCESS_TOKEN)) {
            cookieName = String.valueOf(TokenType.ACCESS_TOKEN).toLowerCase();
        } else if (tokenType.equals(TokenType.REFRESH_TOKEN)) {
            cookieName = String.valueOf(TokenType.REFRESH_TOKEN).toLowerCase();
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
                cookie.setValue(null);
            }
        }
    }
}
