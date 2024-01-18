package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;

public interface IOwnerHandler {

    void createEmployee(EmployeeRequestDto employeeRequestDto);

}