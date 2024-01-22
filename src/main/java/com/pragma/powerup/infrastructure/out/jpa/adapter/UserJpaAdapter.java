package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserModel createUser(UserModel userModel) {
        UserEntity userEntity = userEntityMapper.toUserEntity(userModel);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel findOwnerById(Long ownerId) {
        Optional<UserEntity> userId = userRepository.findById(ownerId);
        if (userId.isPresent()){
            UserEntity foundId = userId.get();
            return userEntityMapper.toUserModel(foundId);
        }
        else {
        return null;
        }
    }

    @Override
    public UserModel findUserByEmail(String email) {
        Optional<UserEntity> userEmail = userRepository.findByEmail(email);
        return userEmail.map(userEntityMapper::toUserModel).orElse(null);
    }

}