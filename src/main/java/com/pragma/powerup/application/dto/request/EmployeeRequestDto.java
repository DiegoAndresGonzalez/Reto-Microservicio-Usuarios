package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {

    private String firstName;
    private String lastName;
    private String dni;
    private String phone;
    private RoleModel role;
    private String email;
    private String password;
    private String restaurantName;

}