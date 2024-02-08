package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.feignclient.IRestaurantFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.security.jwt.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IRestaurantFeignClient restaurantFeignClient;

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

    @Override
    public UserModel saveUserEmployeeRestaurant(UserModel userModel, String restaurantName) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(userModel.getEmail());
        UserEntity foundUser = userEntity.orElse(null);
        RestaurantModel restaurantModel = restaurantFeignClient.fetchRestaurant(TokenHolder.getToken(),restaurantName);
        if (restaurantModel == null){
            throw new DataNotFoundException("El restaurante que ingresaste no existe o no fue encontrado en la base de datos.");
        }
        assert foundUser != null;
        Optional<UserEntity> ownerEntity = userRepository.findByEmail(TokenHolder.getEmail());
        if (ownerEntity.isPresent() && (!Objects.equals(ownerEntity.get().getId(), restaurantModel.getIdOwner()))){
                throw new InvalidInputException("No puedes crear empleados en restaurantes que no te pertenecen");
        }
        restaurantFeignClient.fetchEmployeeRestaurant(TokenHolder.getToken(), foundUser.getId(), restaurantModel.getId());
        return userEntityMapper.toUserModel(foundUser);
}

}

