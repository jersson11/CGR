package com.test.testactivedirectory.infrastructure.persistence.repository.user;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

}
