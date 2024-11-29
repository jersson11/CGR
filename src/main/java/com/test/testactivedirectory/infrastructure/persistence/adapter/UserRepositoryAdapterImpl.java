package com.test.testactivedirectory.infrastructure.persistence.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.user.dto.UserWithRolesRequestDto;
import com.test.testactivedirectory.domain.repository.IUserRoleRepository;
import com.test.testactivedirectory.infrastructure.exception.customException.ResourceNotFoundException;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.role.RoleRepositoryJpa;
import com.test.testactivedirectory.infrastructure.persistence.repository.user.UserRepositoryJpa;

@Component
public class UserRepositoryAdapterImpl implements IUserRoleRepository {

    private final UserRepositoryJpa userRepositoryJpa;

    private final RoleRepositoryJpa roleRepositoryJpa;

    public UserRepositoryAdapterImpl(UserRepositoryJpa userRepositoryJpa, RoleRepositoryJpa roleRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.roleRepositoryJpa = roleRepositoryJpa;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> users = this.userRepositoryJpa.findAll();
        return users;
    }

    @Transactional
    @Override
    public UserEntity assignRolesToUser(UserWithRolesRequestDto requestDto) {
        UserEntity user = this.userRepositoryJpa.findById(requestDto.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "el usuario con id=" + requestDto.getIdUser() + " no existe"));

        List<RoleEntity> roles = this.roleRepositoryJpa.findByIdIn(requestDto.getRoleIds());

        roles.forEach(role -> {
            if (!user.getRoles().contains(role)) {
                user.getRoles().add(role);
            }
        });

        return this.userRepositoryJpa.save(user);
    }

}
