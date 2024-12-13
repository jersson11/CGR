package com.test.testactivedirectory.application.auth.dto;

import java.util.List;

import com.test.testactivedirectory.infrastructure.persistence.entity.Menu.Menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto {

    private String sAMAccountName;
    private String token;
    private Boolean isEnable;
    private List<String> roles;
    private List<Menu> menus;

}
