package com.klik.security;

import com.klik.entity.Uloga;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMs = 1000 * 60 * 60;

    public String generateToken(final String email, final String uloga) {
        return Jwts.builder()
                .setSubject(email)
                .claim("uloga", uloga)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() * expirationMs))
                .signWith(key)
                .compact();
    }

    public String extractUsername(final String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUloga(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("uloga", String.class);
    }
}
