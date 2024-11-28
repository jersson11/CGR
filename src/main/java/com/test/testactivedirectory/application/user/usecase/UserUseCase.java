package com.test.testactivedirectory.application.user.usecase;

import java.util.List;

import com.test.testactivedirectory.application.user.dto.UserWithRolesRequestDto;
import com.test.testactivedirectory.application.user.dto.UserWithRolesResponseDto;

public interface UserUseCase {

    public abstract List<UserWithRolesResponseDto> findAll();

    public abstract UserWithRolesResponseDto assignRolesToUser(UserWithRolesRequestDto requestDto);

}
