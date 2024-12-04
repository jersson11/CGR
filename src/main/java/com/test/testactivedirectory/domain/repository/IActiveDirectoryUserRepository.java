package com.test.testactivedirectory.domain.repository;

import java.util.List;

import com.test.testactivedirectory.domain.models.ActiveDirectoryUserModel;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;
import com.unboundid.ldap.sdk.SearchResultEntry;

public interface IActiveDirectoryUserRepository {
    Boolean checkAccount(String samAccountName, String password);

    List<UserEntity> getAllUsers();
}
