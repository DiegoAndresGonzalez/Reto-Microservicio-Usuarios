package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IAdminHandler;
import com.pragma.powerup.application.mapper.IAdminRequestMapper;
import com.pragma.powerup.application.mapper.IAdminResponseMapper;
import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminHandler implements IAdminHandler {

    private final IAdminServicePort adminServicePort;
    private final IAdminRequestMapper adminRequestMapper;
    private final IAdminResponseMapper adminResponseMapper;

    @Override
    public void createOwner(UserRequestDto userRequestDto) {
        UserModel userModel = adminRequestMapper.toOwnerModel(userRequestDto);
        adminServicePort.createOwner(userModel);
    }

    @Override
    public UserResponseDto findOwnerById(Long ownerId) {
        UserModel userModel = adminServicePort.findOwnerById(ownerId);
        return adminResponseMapper.toResponse(userModel);
    }
}