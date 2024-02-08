package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.ClientRequestDto;
import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;

public interface IUserHandler {

    void createOwner(UserRequestDto userRequestDto);
    void createEmployee(EmployeeRequestDto employeeRequestDto);
    UserResponseDto findOwnerById(Long ownerId);
    UserResponseDto findUserByEmail(String email);
    void createClient(ClientRequestDto clientRequestDto);

}