package com.test.testactivedirectory.application.user.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserWithRolesRequestDto {

    @NotBlank
    private Long idUser;

    @NotNull
    private List<Long> roleIds;
}
