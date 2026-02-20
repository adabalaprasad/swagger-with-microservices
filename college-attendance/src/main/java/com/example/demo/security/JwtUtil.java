package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JwtUtil {

	private static final String SECRET_KEY ="mysecretkeymysecretkeymysecretkeymysecretkey";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private static SecretKey getSignKey() 
    {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String generateToken(String email) 
    {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey())
                .compact();
    }
    
    
    public static String validateTokenAndGetEmail(String token) 
    {
        return Jwts.parser()
                .verifyWith(getSignKey())   
                .build()
                .parseSignedClaims(token)  
                .getPayload()
                .getSubject();
    }


    public static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }
}
