package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IAdminHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.pragma.powerup.domain.utils.Constant.ADMIN_ROLE;

@RestController
@RequestMapping("api/admin/")
@RequiredArgsConstructor
public class AdminRestController {

    private final IAdminHandler adminHandler;

    @Operation(summary = "Create an owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/owner")
    @PreAuthorize("hasRole('"+ADMIN_ROLE+"')")
    public ResponseEntity<Void> createOwner(@RequestBody UserRequestDto userRequestDto){
        adminHandler.createOwner(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("user/{id}")
    @PreAuthorize("hasRole('"+ADMIN_ROLE+"')")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(adminHandler.findOwnerById(id));
    }

    @GetMapping("user/byEmail/{email}")
    @PreAuthorize("hasRole('"+ADMIN_ROLE+"')")
    public ResponseEntity<UserResponseDto> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(adminHandler.findUserByEmail(email));
    }
}