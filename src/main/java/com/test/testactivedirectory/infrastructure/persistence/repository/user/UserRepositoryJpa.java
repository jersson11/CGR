package com.test.testactivedirectory.infrastructure.persistence.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.sAMAccountName = :sAMAccountName")
    Optional<UserEntity> findBySAMAccountName(@Param("sAMAccountName") String sAMAccountName);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.sAMAccountName = :sAMAccountName")
    boolean existsBySAMAccountName(@Param("sAMAccountName") String sAMAccountName);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles WHERE u.sAMAccountName = :sAMAccountName")
    Optional<UserEntity> findBySAMAccountNameWithRoles(@Param("sAMAccountName") String sAMAccountName);
}
