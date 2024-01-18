package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;

public interface IAdminHandler {

    void createOwner(UserRequestDto userRequestDto);
    UserResponseDto findOwnerById(Long ownerId);
    UserResponseDto findUserByEmail(String email);

}