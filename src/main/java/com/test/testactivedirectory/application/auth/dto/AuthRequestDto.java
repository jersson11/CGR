package com.test.testactivedirectory.application.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    private String sAMAccountName;
    private String password;   
    private String token;

}
