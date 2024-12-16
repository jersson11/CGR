package com.cgr.base.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActiveDirectoryUserModel {

    // Atributos básicos del usuario
    private String cn;
    private String givenName;
    private String sn;
    private String displayName;
    private String userPrincipalName;
    private String sAMAccountName;
    private String mail;

    // Atributos relacionados con la cuenta y el directorio
    private String distinguishedName;
    private String objectCategory;
    private String objectGUID;
    private long accountExpires;
    private long pwdLastSet;
    private int userAccountControl;
    private int primaryGroupID;

    // Atributos relacionados con la auditoría
    private long lastLogonTimestamp;
    private int logonCount;
    private int badPwdCount;
    private String dSCorePropagationData;

}
