package com.test.testactivedirectory.domain.repository;

import com.test.testactivedirectory.domain.models.UserModel;

public interface IUserRepository {
     
    UserModel findBySAMAccountName(String sAMAccountName);
    
    // UserModel save(UserModel user);
    // UserModel update(UserModel user);
    // void delete(UserModel user);
    // List<UserModel> findAll();
}
