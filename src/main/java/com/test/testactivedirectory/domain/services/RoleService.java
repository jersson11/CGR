package com.test.testactivedirectory.domain.services;

import java.util.List;

import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

public interface RoleService {
    public abstract List<RoleEntity> getAll();

    public abstract RoleEntity getById(Long idRole);

    public abstract RoleEntity create(RoleEntity roleEntity);

}
