package com.example.loginsystem;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class JwtTokenProvider {
    private String secretKey = "yourSecretKey";

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 604800000L); // Token valid for 7 days

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
