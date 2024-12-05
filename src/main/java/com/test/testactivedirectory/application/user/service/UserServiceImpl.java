package com.test.testactivedirectory.application.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.user.dto.UserWithRolesRequestDto;
import com.test.testactivedirectory.application.user.dto.UserWithRolesResponseDto;
import com.test.testactivedirectory.application.user.usecase.UserUseCase;
import com.test.testactivedirectory.domain.repository.IUserRoleRepository;
import com.test.testactivedirectory.infrastructure.utilities.DtoMapper;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserUseCase {

    private final IUserRoleRepository userRoleRepository;

    private final DtoMapper dtoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserWithRolesResponseDto> findAll() {
        List<UserWithRolesResponseDto> users = new ArrayList<>();
        this.userRoleRepository.findAll().forEach(user -> {
            var userResponsive = new UserWithRolesResponseDto();
            userResponsive.setIdUser(user.getId());
            userResponsive.setUserName(user.getSAMAccountName());
            userResponsive.setFullName(user.getFullName());
            userResponsive.setEmail(user.getEmail());
            userResponsive.setPhone(user.getPhone());
            userResponsive.setEnabled(user.isEnabled());
            userResponsive.setDateModify(user.getDateModify());
            userResponsive.setCargo(user.getCargo());

            userResponsive.addRole(user.getRoles());

            users.add(userResponsive);
        });
        return users;
    }

    @Transactional
    @Override
    public UserWithRolesResponseDto assignRolesToUser(UserWithRolesRequestDto requestDto) {
        UserEntity userEntity = this.userRoleRepository.assignRolesToUser(requestDto);
        var userResponsive = new UserWithRolesResponseDto();
        userResponsive.setIdUser(userEntity.getId());
        userResponsive.setUserName(userEntity.getSAMAccountName());
        userResponsive.addRole(userEntity.getRoles());
        return userResponsive;
    }

}
