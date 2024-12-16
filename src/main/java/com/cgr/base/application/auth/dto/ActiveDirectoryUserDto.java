package com.cgr.base.application.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveDirectoryUserDto {

    // Atributos básicos del usuario
    private String cn;
    private String givenName;
    private String sn;
    private String displayName;
    private String userPrincipalName;
    private String sAMAccountName;
    private String mail;
    private String token;
    // Atributos relacionados con la cuenta y el directorio
    private String distinguishedName;
    private String objectCategory;
    private String objectGUID;
    private long accountExpires;
    private long pwdLastSet;
    private int userAccountControl;
    private int primaryGroupID;
    private String objectSid;
    private int sAMAccountType;

    // Atributos relacionados con auditoría
    private long lastLogonTimestamp;
    private int logonCount;
    private int badPwdCount;
    private String dSCorePropagationData;
    private long badPasswordTime;
    private long lastLogoff;
    private long lastLogon;

    // Atributos adicionales
    private String postOfficeBox;
    private String name;
    private String whenCreated;
    private String whenChanged;
    private String instanceType;
    private String uSNCreated;
    private String uSNChanged;
    private int codePage;
    private int countryCode;

}
