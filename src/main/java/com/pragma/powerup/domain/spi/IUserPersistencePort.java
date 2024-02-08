package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;

public interface IUserPersistencePort {

    UserModel createUser(UserModel userModel);
    UserModel findOwnerById(Long ownerId);
    UserModel findUserByEmail(String email);
    UserModel saveUserEmployeeRestaurant(UserModel userModel, String restaurantName);
}