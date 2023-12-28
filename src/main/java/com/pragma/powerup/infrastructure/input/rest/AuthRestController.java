package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.AuthRequest;
import com.pragma.powerup.application.dto.response.AuthResponse;
import com.pragma.powerup.infrastructure.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthRestController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authentication(request));
    }

}