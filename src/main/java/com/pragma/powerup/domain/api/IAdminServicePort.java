package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

public interface IAdminServicePort {

    void createOwner(UserModel userModel);
    UserModel findOwnerById(Long ownerId);
    UserModel findUserByEmail(String email);
}