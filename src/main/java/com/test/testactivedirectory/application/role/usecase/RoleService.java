package com.test.testactivedirectory.application.role.usecase;

import java.util.List;

import com.test.testactivedirectory.application.role.dto.RoleRequestDto;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

public interface RoleService {
    public abstract List<RoleRequestDto> findAll();

    public abstract RoleRequestDto findById(Long idRole);

    public abstract RoleRequestDto create(RoleEntity roleEntity);

    public abstract RoleRequestDto update(RoleEntity roleEntity);

    public abstract RoleRequestDto activateOrDeactivate(Long idRole);
}
