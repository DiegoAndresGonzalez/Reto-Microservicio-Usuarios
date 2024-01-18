package com.pragma.powerup.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.pragma.powerup.domain.utils.Constant.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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