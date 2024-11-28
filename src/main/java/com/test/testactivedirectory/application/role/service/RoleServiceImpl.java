package com.test.testactivedirectory.application.role.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.role.interfaces.RoleService;
import com.test.testactivedirectory.domain.repository.IRoleRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final IRoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RoleEntity> findAll() {
        return this.roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public RoleEntity findById(Long idRole) {
        return this.roleRepository.findById(idRole);
    }

    @Transactional
    @Override
    public RoleEntity create(RoleEntity roleEntity) {
        return this.roleRepository.create(roleEntity);
    }

    @Transactional
    @Override
    public RoleEntity update(RoleEntity roleEntity) {
        return this.roleRepository.update(roleEntity);
    }

    @Transactional
    @Override
    public RoleEntity activateOrDeactivate(Long idRole) {
        return this.roleRepository.activateOrDeactivate(idRole);
    }

}
