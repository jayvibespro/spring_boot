package com.jaycode.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "ed9332b6cd3ac41c91f47af58b7afce41cbf496d20f06bc76c5546fda55009c3087904e99b7fa2a8aaec9bb12d8a2310c7ac571531f6ff33430ce8a28500eb9eae0e2ca74ef4558f9c47052e2c926444b06d3127453848089ee1d3ea75ce6267a7118733c7a2c248ad3650d16fe7f41ce56f3a163891fe9856ab96a76cee4b836eacfc02c8b179ba605f6ea425c5528f0339b988b5e9bf03f96e47f3dcebf372512199f954e561321d0ee70f8785066bbd4ffc522fdc703de9bb41e37273e220dc1bfc9f5742b15c13b556f6fa1cf373981278cd5fc9e2d97d18bc8cb8dc994c7cacd2406d18a084db51896d136c14d3fb900f003848dd82ca55f8b041f5441d"; // Use a strong, secure key

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
