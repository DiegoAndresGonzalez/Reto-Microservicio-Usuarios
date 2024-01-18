package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.handler.IOwnerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pragma.powerup.domain.utils.Constant.OWNER_ROLE;

@RestController
@RequestMapping("api/owner/")
@RequiredArgsConstructor
public class OwnerRestController {

    private final IOwnerHandler ownerHandler;

    @PostMapping("employee")
    @PreAuthorize("hasRole('"+OWNER_ROLE+"')")
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        ownerHandler.createEmployee(employeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}