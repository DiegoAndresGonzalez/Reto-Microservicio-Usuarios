package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public RoleModel findRoleByName(String roleName) {
        RoleEntity roleEntity = roleRepository.findByRole(roleName);
        return roleEntityMapper.toRoleModel(roleEntity);
    }

    @Override
    public RoleModel findRoleById(Long roleId) {
        Optional<RoleEntity> foundRole = roleRepository.findById(roleId);
        return foundRole.map(roleEntityMapper::toRoleModel).orElse(null);
    }

}