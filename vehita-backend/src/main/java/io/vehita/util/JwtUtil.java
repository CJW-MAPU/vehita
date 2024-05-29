package io.vehita.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.vehita.exception.InvalidTokenException;
import io.vehita.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final int ONE_DAY = 1000 * 60 * 60 * 24;
    private final int TWO_WEEK = 1000 * 60 * 60 * 24 * 14;

    private final Key key;

    public JwtUtil(
            @Value("${jwt.secret}") String secret
    ) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String userId, TokenType type) {
        long now = new Date().getTime();

        Date expiredAt;

        if (type.equals(TokenType.ACCESS_TOKEN)) {
            expiredAt = new Date(now + ONE_DAY);
        } else if (type.equals(TokenType.REFRESH_TOKEN)) {
            expiredAt = new Date(now + TWO_WEEK);
        } else {
            expiredAt = null;
        }

        return Jwts.builder()
                .claim("username", userId)
                .setExpiration(expiredAt)
                .signWith(key)
                .compact();
    }

    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException(token);
        } catch (SignatureException e) {
            throw new InvalidTokenException(token);
        }
    }

}
