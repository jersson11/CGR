package com.test.testactivedirectory.infrastructure.persistence.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.domain.repository.IRoleRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.RoleRepositoryJpa;

@Component
public class RoleRepositoryAdapterImpl implements IRoleRepository {

    private final RoleRepositoryJpa roleRepositoryJpa;

    public RoleRepositoryAdapterImpl(RoleRepositoryJpa roleRepositoryJpa) {
        this.roleRepositoryJpa = roleRepositoryJpa;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleEntity> findAll() {
        return this.roleRepositoryJpa.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public RoleEntity findById(Long idRole) {
        return this.roleRepositoryJpa.findById(idRole).orElseThrow(
                () -> new UnsupportedOperationException());
    }

    @Transactional
    @Override
    public RoleEntity create(RoleEntity roleEntity) {
        roleEntity.setId(null);
        return this.roleRepositoryJpa.save(roleEntity);
    }

    @Transactional
    @Override
    public RoleEntity update(RoleEntity roleEntity) {
        return this.roleRepositoryJpa.save(roleEntity);
    }

    @Transactional
    @Override
    public RoleEntity activateOrDeactivate(Long idRole) {
        RoleEntity roleEntity = this.roleRepositoryJpa.findById(idRole).orElseThrow(
                () -> new UnsupportedOperationException());

        if (roleEntity.isEnable()) roleEntity.setEnable(false);
        else roleEntity.setEnable(true);

        return roleEntity;
    }

}
