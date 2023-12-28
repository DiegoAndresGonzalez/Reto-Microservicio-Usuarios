package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IAdminPersistencePort;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.domain.utils.Constant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserUseCase implements IAdminServicePort {

    private final IAdminPersistencePort administratorPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    public UserUseCase(IAdminPersistencePort administratorPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.administratorPersistencePort = administratorPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void createOwner(UserModel userModel) {
        if (userModel.getEmail() == null||!userModel.isEmail()){
            throw new DomainException("Escribe tu correo correctamente.");
        }
        if (userModel.getPhone() == null||!userModel.getPhone().matches("^\\+?[0-9]{1,13}$")){
            throw new DomainException("Escribe tu número de celular correctamente.");
        }
        if (userModel.getDni() == null||!userModel.getDni().matches(("^[0-9]+$"))){
            throw new DomainException("Escribe tu numero de identificacion correctamente.");
        }
        long age = ChronoUnit.YEARS.between(userModel.getBirthdate(), LocalDate.now());
        if (age < 18){
            throw new DomainException("El propietario no puede tener menos de 18 años.");
        }
        RoleModel roleModel = rolePersistencePort.findRoleByName(Constant.PROPRIETARY_ROLE);
        if (roleModel == null){
            throw new DomainException("El rol no existe.");
        }
        userModel.setRole(roleModel);
        administratorPersistencePort.createOwner(userModel);
    }

    @Override
    public UserModel findOwnerById(Long ownerId) {
        return administratorPersistencePort.findOwnerById(ownerId);
    }
}