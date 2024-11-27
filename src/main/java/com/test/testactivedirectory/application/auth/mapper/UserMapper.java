package com.test.testactivedirectory.application.auth.mapper;

import java.util.function.Function;

import com.test.testactivedirectory.application.auth.dto.AuthRequestDto;
import com.test.testactivedirectory.domain.models.UserModel;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;

public enum UserMapper implements Function<UserEntity, AuthRequestDto> {
    INSTANCE;

    @Override
    public AuthRequestDto apply(UserEntity userEntity) {

        if (userEntity != null) {
            AuthRequestDto userRequestDto = new AuthRequestDto();
            userRequestDto.setSAMAccountName(userEntity.getSAMAccountName());
            userRequestDto.setPassword(userEntity.getPassword());
            return userRequestDto;
        }
        return null;
    }

    public UserModel toUserEntity(UserEntity userREntity) {
        UserModel userModel = new UserModel();

        userModel.setId(userREntity.getId());
        userModel.setSAMAccountName(userREntity.getSAMAccountName());
        userModel.setPassword(userREntity.getPassword());

        return userModel;
    }

    public AuthRequestDto toUserRequestDto(UserModel userModel) {
        AuthRequestDto userRequestDto = new AuthRequestDto();
        userRequestDto.setSAMAccountName(userModel.getSAMAccountName());
        userRequestDto.setPassword(userModel.getPassword());
        return userRequestDto;
    }
}
