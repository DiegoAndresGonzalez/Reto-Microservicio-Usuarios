package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOwnerServicePort;
import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IOwnerPersistencePort;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import lombok.AllArgsConstructor;

import static com.pragma.powerup.domain.utils.Constant.EMPLOYEE_ROLE;

@AllArgsConstructor
public class OwnerUseCase implements IOwnerServicePort {

    private final IOwnerPersistencePort ownerPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    @Override
    public void createEmployee(UserModel userModel) {
        validateRequiredFields(userModel);
        RoleModel roleModel = rolePersistencePort.findRoleByName(EMPLOYEE_ROLE);
        if (roleModel == null) {
            throw new DataNotFoundException("El rol no existe.");
        }
        validateRole(userModel.getRole());
        userModel.setRole(roleModel);
        ownerPersistencePort.createEmployee(userModel);
    }

    private void validateRequiredFields(UserModel employee) {
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty() ||
                employee.getLastName() == null || employee.getLastName().trim().isEmpty() ||
                employee.getDni() == null || employee.getDni().trim().isEmpty() ||
                employee.getPhone() == null || employee.getPhone().trim().isEmpty() ||
                employee.getEmail() == null || employee.getEmail().trim().isEmpty() ||
                employee.getRole() == null || employee.getRole().getId() == null ||
                employee.getPassword() == null || employee.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Todos los campos son obligatorios al crear una cuenta para empleado.");
        }
    }

    private void validateRole(RoleModel roleModel) {
        if (roleModel == null || roleModel.getId() == null || roleModel.getId() != 2) {
            throw new InvalidInputException("El usuario debe tener el rol 'empleado'.");
        }
    }

}