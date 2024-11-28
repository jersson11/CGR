package com.test.testactivedirectory.application.role.usecase;

import java.util.List;

import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

public interface RoleService {
    public abstract List<RoleEntity> findAll();

    public abstract RoleEntity findById(Long idRole);

    public abstract RoleEntity create(RoleEntity roleEntity);

    public abstract RoleEntity update(RoleEntity roleEntity);

    public abstract RoleEntity activateOrDeactivate(Long idRole);
}
