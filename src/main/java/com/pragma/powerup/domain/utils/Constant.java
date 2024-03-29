package com.pragma.powerup.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.Key;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String OWNER_ROLE = "PROPIETARIO";
    public static final String ADMIN_ROLE = "ADMINISTRADOR";
    public static final String EMPLOYEE_ROLE = "EMPLEADO";
    public static final String CLIENT_ROLE = "CLIENTE";
    public static final String EMAIL_REGEX_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PHONE_REGEX_PATTERN = "^\\+?[0-9]{10,13}$";
    public static final String DNI_REGEX_PATTERN = "^[0-9]{8,10}$";
    public static final String SECRET_KEY = "bLbjRGox3VMtwGXzsFh51hL4AADgiqjMTxbzId3RSWJd9aU3KFC8flGRrrNIprvz";

}