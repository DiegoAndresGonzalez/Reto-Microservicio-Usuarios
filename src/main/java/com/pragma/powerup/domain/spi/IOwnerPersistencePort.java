package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;

public interface IOwnerPersistencePort {

    UserModel createEmployee(UserModel userModel);

}