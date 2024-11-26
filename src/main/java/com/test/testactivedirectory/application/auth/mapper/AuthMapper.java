package com.test.testactivedirectory.application.auth.mapper;

import java.util.function.Function;

import com.test.testactivedirectory.application.auth.dto.AuthRequestDto;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;

public enum AuthMapper implements Function<UserEntity, AuthRequestDto> {
    INSTANCE;

    @Override
    public AuthRequestDto apply(UserEntity t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }

}
