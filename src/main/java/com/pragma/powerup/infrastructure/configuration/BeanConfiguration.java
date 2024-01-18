package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.api.IOwnerServicePort;
import com.pragma.powerup.domain.spi.IAdminPersistencePort;
import com.pragma.powerup.domain.spi.IOwnerPersistencePort;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.domain.usecase.AdminUseCase;
import com.pragma.powerup.domain.usecase.OwnerUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.AdminJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.OwnerJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRolePersistencePort rolePersistencePort;

    @Bean
    public IAdminPersistencePort adminPersistencePort(PasswordEncoder passwordEncoder){
        return new AdminJpaAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    public IAdminServicePort adminServicePort(IAdminPersistencePort adminPersistencePort,
                                              IRolePersistencePort rolePersistencePort){
        return new AdminUseCase(adminPersistencePort, rolePersistencePort);
    }

    @Bean
    public IOwnerPersistencePort ownerPersistencePort(PasswordEncoder passwordEncoder){
        return new OwnerJpaAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    public IOwnerServicePort ownerServicePort(IOwnerPersistencePort ownerPersistencePort,
                                              IRolePersistencePort rolePersistencePort){
        return new OwnerUseCase(ownerPersistencePort,rolePersistencePort);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado."));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}