package com.test.testactivedirectory.domain.repository;

import com.test.testactivedirectory.domain.models.ActiveDirectoryUserModel;
import com.unboundid.ldap.sdk.SearchResultEntry;

public interface IActiveDirectoryUserRepository {
    Boolean checkAccount(String samAccountName, String password);
}
