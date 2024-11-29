package com.test.testactivedirectory.application.user.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserWithRolesRequestDto {

    @NotNull
    private Long idUser;

    @NotNull
    private List<Long> roleIds;
}
