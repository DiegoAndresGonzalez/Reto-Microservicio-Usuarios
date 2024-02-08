package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String email;
    private RoleModel role;
}