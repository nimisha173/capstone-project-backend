package com.ust.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> claims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    Claims extractClaims(String token);

    <T> T extractClaim(String token, Function<Claims, T> resolver);
}
