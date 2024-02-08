package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.ClientRequestDto;
import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.pragma.powerup.domain.utils.Constant.*;

@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Create an owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/owner")
    @PreAuthorize("hasRole('"+ADMIN_ROLE+"')")
    public ResponseEntity<Void> createOwner(@RequestBody UserRequestDto userRequestDto){
        userHandler.createOwner(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('"+ADMIN_ROLE+"')")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userHandler.findOwnerById(id));
    }

    @GetMapping("byEmail/{email}")
    @PreAuthorize("hasAnyRole('"+ADMIN_ROLE+"')")
    public ResponseEntity<UserResponseDto> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userHandler.findUserByEmail(email));
    }

    @PostMapping("employee")
    @PreAuthorize("hasRole('"+OWNER_ROLE+"')")
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        userHandler.createEmployee(employeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("client")
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDto clientRequestDto){
        userHandler.createClient(clientRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("data")
    @PreAuthorize("hasAnyRole('"+ADMIN_ROLE+"','"+OWNER_ROLE+"','"+CLIENT_ROLE+"','"+EMPLOYEE_ROLE+"')")
    public ResponseEntity<UserResponseDto> findLoggedUser(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserResponseDto userResponseDto = userHandler.findUserByEmail(userDetails.getUsername());
        return  ResponseEntity.ok(userResponseDto);
    }

}