package com.pragma.powerup.infrastructure.security.auth;

import com.pragma.powerup.application.dto.request.AuthRequest;
import com.pragma.powerup.application.dto.response.AuthResponse;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse authentication(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword())
        );
        UserEntity authUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String role = String.valueOf(userRepository.findRoleByEmail(request.getEmail()));
        String jwtToken = jwtService.generateTokenWithRole(authUser, role);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}