package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;

public interface IAdminPersistencePort {

    UserModel createOwner(UserModel userModel);
    UserModel findOwnerById(Long ownerId);

}