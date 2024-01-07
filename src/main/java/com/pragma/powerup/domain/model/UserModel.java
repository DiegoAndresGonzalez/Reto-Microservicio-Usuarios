package com.pragma.powerup.domain.model;
import java.time.LocalDate;

import static com.pragma.powerup.domain.utils.Constant.*;

public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String phone;
    private LocalDate birthdate;
    private String email;
    private String password;
    private RoleModel role;

    public UserModel(Long id, String firstName, String lastName, String dni, String phone, LocalDate birthdate, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public boolean isEmail(){
        return this.email.matches(EMAIL_REGEX_PATTERN);
    }

    public boolean isPhone(){
        return this.phone.matches(PHONE_REGEX_PATTERN);
    }

    public boolean isDni(){
        return this.dni.matches(DNI_REGEX_PATTERN);
    }

}