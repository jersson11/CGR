package com.test.testactivedirectory.domain.repository;

import java.util.List;

import com.test.testactivedirectory.application.user.dto.UserWithRolesRequestDto;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;

public interface IUserRoleRepository {

    public abstract List<UserEntity> findAll();

    public abstract UserEntity assignRolesToUser(UserWithRolesRequestDto requestDto);

}
