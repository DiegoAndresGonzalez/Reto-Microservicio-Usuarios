package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.exception.DuplicateEmailException;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.domain.utils.Constant;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import static com.pragma.powerup.domain.utils.Constant.CLIENT_ROLE;
import static com.pragma.powerup.domain.utils.Constant.EMPLOYEE_ROLE;

@AllArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    @Override
    public void createOwner(UserModel userModel) {
        validateFirstName(userModel);
        validateLastName(userModel);
        validateDni(userModel);
        validateEmail(userModel);
        validatePhone(userModel);
        validateBirthdate(userModel);
        validatePassword(userModel);
        RoleModel roleModel = rolePersistencePort.findRoleByName(Constant.OWNER_ROLE);
        validateRoleExistence(userModel,roleModel);
        userPersistencePort.createUser(userModel);
    }

    private void validateFirstName(UserModel userModel) {
        if (userModel.getFirstName() == null || userModel.getFirstName().trim().isEmpty()) {
            throw new InvalidInputException("El nombre del usuario no puede estar vacío..");
        }
    }

    private void validateLastName(UserModel userModel){
        if (userModel.getLastName() == null || userModel.getLastName().trim().isEmpty()) {
            throw new InvalidInputException("El apellido del usuario no puede estar vacío.");
        }
    }

    private void validateEmail(UserModel userModel) {
        if (userModel.getEmail() == null || userModel.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("El correo no puede estar vacío.");
        }

        UserModel emailModel = userPersistencePort.findUserByEmail(userModel.getEmail());
        if (emailModel != null) {
            throw new DuplicateEmailException("Este correo ya está registrado.");
        } else if (!userModel.isEmail()) {
            throw new InvalidInputException("Escribe tu correo correctamente.");
        }
    }

    private void validatePhone(UserModel userModel) {
        if (userModel.getPhone() == null || userModel.getPhone().trim().isEmpty()) {
            throw new InvalidInputException("El número de celular no puede estar vacío.");
        } else if (!userModel.isPhone()) {
            throw new InvalidInputException("Escribe tu número de celular correctamente.");
        }
    }

    private void validateDni(UserModel userModel) {
        if (userModel.getDni() == null || userModel.getDni().trim().isEmpty()) {
            throw new InvalidInputException("El número de identificación no puede estar vacío.");
        } else if (!userModel.isDni()) {
            throw new InvalidInputException("Escribe tu número de identificación correctamente.");
        }
    }

    private void validateBirthdate(UserModel userModel) {
        if (userModel.getBirthdate() == null || userModel.getBirthdate().toString().trim().isEmpty()){
            throw new InvalidInputException("La edad no puede estar vacía.");
        } else if (userModel.getBirthdate().isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidInputException("El propietario debe ser mayor de edad.");
        }
    }

    private void validatePassword(UserModel userModel){
        if (userModel.getPassword() == null || userModel.getPassword().trim().isEmpty()){
            throw new InvalidInputException("La contraseña no puede estar vacía.");
        }
    }

    @Override
    public void createEmployee(UserModel userModel, String restaurantName) {
        validateRequiredFields(userModel);
        RoleModel roleModel = rolePersistencePort.findRoleById(userModel.getRole().getId());
        validateRoleExistence(userModel,roleModel);
        validateEmail(userModel);
        validatePhone(userModel);
        validateDni(userModel);
        validateEmployeeRole(userModel.getRole());
        userModel.setRole(roleModel);
        UserModel newUser = userPersistencePort.createUser(userModel);
        userPersistencePort.saveUserEmployeeRestaurant(newUser,restaurantName);

    }

    @Override
    public void createClient(UserModel userModel) {
        validateRequiredFields(userModel);
        RoleModel roleModel = rolePersistencePort.findRoleById(userModel.getRole().getId());
        validateRoleExistence(userModel,roleModel);
        validateEmail(userModel);
        validatePhone(userModel);
        validateDni(userModel);
        validateClientRole(userModel);
        userModel.setRole(roleModel);
        userPersistencePort.createUser(userModel);
    }

    private void validateRequiredFields(UserModel userModel) {
        if (userModel.getFirstName() == null || userModel.getFirstName().trim().isEmpty() ||
                userModel.getLastName() == null || userModel.getLastName().trim().isEmpty() ||
                userModel.getDni() == null || userModel.getDni().trim().isEmpty() ||
                userModel.getPhone() == null || userModel.getPhone().trim().isEmpty() ||
                userModel.getEmail() == null || userModel.getEmail().trim().isEmpty() ||
                userModel.getRole().getId() == null ||
                userModel.getPassword() == null || userModel.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Todos los campos son obligatorios al crear una cuenta.");
        }
    }

    private void validateEmployeeRole(RoleModel roleModel) {
        if (!roleModel.getRole().equals(EMPLOYEE_ROLE)) {
            throw new InvalidInputException("El usuario debe tener el rol 'empleado'.");
        }
    }

    private void validateClientRole(UserModel userModel) {
        if (!userModel.getRole().getRole().equals(CLIENT_ROLE)) {
            throw new InvalidInputException("El usuario debe tener el rol 'cliente'.");
        }
    }

    private void validateRoleExistence(UserModel userModel, RoleModel roleModel) {
        if (roleModel == null) {
            throw new DataNotFoundException("El rol no existe.");
        }
        userModel.setRole(roleModel);
    }

    @Override
    public UserModel findOwnerById(Long ownerId) {
        return userPersistencePort.findOwnerById(ownerId);
    }

    @Override
    public UserModel findUserByEmail(String email) {
        return userPersistencePort.findUserByEmail(email);
    }
}