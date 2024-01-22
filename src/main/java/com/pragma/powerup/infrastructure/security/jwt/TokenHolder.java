package com.pragma.powerup.infrastructure.security.jwt;

public class TokenHolder {
    private static final ThreadLocal<String> jwtHolder = new ThreadLocal<>();

    public static void setToken(String token) {
        jwtHolder.set(token);
    }

    public static String getToken() {
        return jwtHolder.get();
    }

    public static void clear() {
        jwtHolder.remove();
    }
}