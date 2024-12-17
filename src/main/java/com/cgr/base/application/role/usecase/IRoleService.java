package com.cgr.base.application.role.usecase;

import java.util.List;

import com.cgr.base.application.role.dto.RoleRequestDto;
import com.cgr.base.infrastructure.persistence.entity.RoleEntity;

public interface IRoleService {
    public abstract List<RoleRequestDto> findAll();

    public abstract RoleRequestDto findById(Long idRole);

    public abstract RoleRequestDto create(RoleEntity roleEntity);

    public abstract RoleRequestDto update(RoleEntity roleEntity);

    public abstract RoleRequestDto activateOrDeactivate(Long idRole);
}
