package com.project.tmartweb.jwt;

import com.project.tmartweb.models.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration;

    @SuppressWarnings("deprecation")
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final Logger logger = Logger.getLogger(JwtTokenUtil.class.getName());

    public String generateToken(User user) {
        Map<String, Object> claims = Map.of(
                "id", user.getId());
        try {
            @SuppressWarnings("deprecation")
            String token = Jwts.builder()
                    .claims(claims)
                    .subject(user.getUsername())
                    .expiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
            return token;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // private Key getSecretKey() {
    // byte[] keyBytes = secretKey.getBytes();
    // return Keys.hmacShaKeyFor(keyBytes);
    // }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    public String getUserNameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
