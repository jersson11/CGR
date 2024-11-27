package com.test.testactivedirectory.infrastructure.persistence.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.test.testactivedirectory.domain.repository.IRoleRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.RoleRepositoryJpa;

@Component
public class RoleRepositoryAdapterImpl implements IRoleRepository {

    private final RoleRepositoryJpa roleRepositoryJpa;

    public RoleRepositoryAdapterImpl(RoleRepositoryJpa roleRepositoryJpa) {
        this.roleRepositoryJpa = roleRepositoryJpa;
    }

    @Override
    public List<RoleEntity> findAll() {

        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
