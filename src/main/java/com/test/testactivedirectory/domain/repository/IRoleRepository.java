package com.test.testactivedirectory.domain.repository;

import java.util.List;

import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

public interface IRoleRepository {

    public abstract List<RoleEntity> findAll();

}
