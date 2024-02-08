package com.pragma.powerup.infrastructure.security.jwt;

public class TokenHolder {
    private static final ThreadLocal<String> jwtHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> emailHolder = new ThreadLocal<>();

    public static void setEmail(String email){
        emailHolder.set(email);
    }

    public static String getEmail(){
        return emailHolder.get();
    }

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