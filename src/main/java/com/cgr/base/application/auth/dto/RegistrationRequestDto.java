package com.cgr.base.application.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {
    // private String firstName;
    private String sAMAccountName;
    private String password;   
    // private String lastName;
    // private String email;
    // private String password;
    // // private List<RoleDto> roles;
    // private String codeAdmin;
    // private String numberPhone;
    // private Boolean enabled;
    // private String position;
}