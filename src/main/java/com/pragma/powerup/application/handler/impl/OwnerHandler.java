package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.handler.IOwnerHandler;
import com.pragma.powerup.application.mapper.IOwnerRequestMapper;
import com.pragma.powerup.domain.api.IOwnerServicePort;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler {

    private final IOwnerRequestMapper ownerRequestMapper;
    private final IOwnerServicePort ownerServicePort;

    @Override
    public void createEmployee(EmployeeRequestDto employeeRequestDto) {
        UserModel userModel = ownerRequestMapper.toEmployeeModel(employeeRequestDto);
        ownerServicePort.createEmployee(userModel);
    }
}