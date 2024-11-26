package com.test.testactivedirectory.application.auth.dto;

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
    private String password;
    private String token;
    private Boolean isEnable;

}
