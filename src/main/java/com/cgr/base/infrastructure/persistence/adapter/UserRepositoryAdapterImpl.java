package com.cgr.base.infrastructure.persistence.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cgr.base.application.user.dto.UserWithRolesRequestDto;
import com.cgr.base.domain.repository.IUserRoleRepository;
import com.cgr.base.infrastructure.exception.customException.ResourceNotFoundException;
import com.cgr.base.infrastructure.persistence.entity.RoleEntity;
import com.cgr.base.infrastructure.persistence.entity.UserEntity;
import com.cgr.base.infrastructure.persistence.repository.role.RoleRepositoryJpa;
import com.cgr.base.infrastructure.persistence.repository.user.UserRepositoryJpa;

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
        user.setRoles(roles);
        return this.userRepositoryJpa.save(user);
    }

}
