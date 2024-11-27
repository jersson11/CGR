package com.test.testactivedirectory.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, Long> {

}
