package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.ClientRequestDto;
import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void createOwner(UserRequestDto userRequestDto) {
        UserModel userModel = userRequestMapper.toOwnerModel(userRequestDto);
        userServicePort.createOwner(userModel);
    }

    @Override
    public UserResponseDto findOwnerById(Long ownerId) {
        UserModel userModel = userServicePort.findOwnerById(ownerId);
        return userResponseMapper.toResponse(userModel);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        UserModel userModel = userServicePort.findUserByEmail(email);
        return userResponseMapper.toResponse(userModel);
    }

    @Override
    public void createClient(ClientRequestDto clientRequestDto) {
        UserModel clientModel = userRequestMapper.toClientModel(clientRequestDto);
        userServicePort.createClient(clientModel);
    }

    @Override
    public void createEmployee(EmployeeRequestDto employeeRequestDto) {
        UserModel userModel = userRequestMapper.toEmployeeModel(employeeRequestDto);
        userServicePort.createEmployee(userModel);
    }
}