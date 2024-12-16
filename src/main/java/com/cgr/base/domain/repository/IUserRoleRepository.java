package com.cgr.base.domain.repository;

import java.util.List;

import com.cgr.base.application.user.dto.UserWithRolesRequestDto;
import com.cgr.base.infrastructure.persistence.entity.UserEntity;

public interface IUserRoleRepository {

    public abstract List<UserEntity> findAll();

    public abstract UserEntity assignRolesToUser(UserWithRolesRequestDto requestDto);

}
