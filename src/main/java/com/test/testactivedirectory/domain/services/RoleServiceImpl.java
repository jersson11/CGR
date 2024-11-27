package com.test.testactivedirectory.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.RoleRepositoryJpa;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepositoryJpa roleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RoleEntity> getAll() {
        return this.roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public RoleEntity getById(Long idRole) {
        // TODO Aquí se debe instancias un tipo de error personalizado en caso de no
        // encontrar la entidad
        return this.roleRepository.findById(idRole).orElseThrow(null);
    }

    @Transactional
    @Override
    public RoleEntity create(RoleEntity roleEntity) {
        roleEntity.setId(null);
        return this.roleRepository.save(roleEntity);
    }

}
