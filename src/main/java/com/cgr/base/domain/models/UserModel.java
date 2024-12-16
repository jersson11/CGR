package com.cgr.base.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private Long id;
    private String sAMAccountName;
    private String password;    
    
}
