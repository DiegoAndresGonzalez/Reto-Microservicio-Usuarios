package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

public interface IUserServicePort {

    void createOwner(UserModel userModel);
    void createEmployee(UserModel userModel);
    void createClient(UserModel userModel);
    UserModel findOwnerById(Long ownerId);
    UserModel findUserByEmail(String email);


}