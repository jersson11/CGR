package com.cgr.base.application.user.usecase;

import java.util.List;

import com.cgr.base.application.user.dto.UserWithRolesRequestDto;
import com.cgr.base.application.user.dto.UserWithRolesResponseDto;

public interface UserUseCase {

    public abstract List<UserWithRolesResponseDto> findAll();

    public abstract UserWithRolesResponseDto assignRolesToUser(UserWithRolesRequestDto requestDto);

}
