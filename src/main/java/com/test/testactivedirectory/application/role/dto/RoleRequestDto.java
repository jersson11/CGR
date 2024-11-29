package com.test.testactivedirectory.application.role.dto;

import lombok.Data;

@Data
public class RoleRequestDto {

    private Long id;

    private String name;

    private boolean enable;

    private String description;

}
