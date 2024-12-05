package com.test.testactivedirectory.application.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.user.usecase.UserSynchronizerUseCase;
import com.test.testactivedirectory.domain.repository.IActiveDirectoryUserRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.user.UserRepositoryJpa;

@Service
public class SyncActiveDirectoryUsers implements UserSynchronizerUseCase {

    @Autowired
    private IActiveDirectoryUserRepository directoryUserRepository;

    @Autowired
    private UserRepositoryJpa userRepositoryDB;

    @Transactional
    @Override
    public Boolean synchronizeUsers() {
        List<UserEntity> usersAD = this.directoryUserRepository.getAllUsers();
        try {
            usersAD.forEach(userAD -> {
                if (this.userRepositoryDB.existsBySAMAccountName(userAD.getSAMAccountName())) {

                    UserEntity userDB = this.userRepositoryDB.findBySAMAccountName(userAD.getSAMAccountName()).get();
                    if (!userDB.getDateModify().equals(userAD.getDateModify())) {
                        userDB.mapActiveDirectoryUser(userAD);
                        this.userRepositoryDB.save(userDB);
                    }

                } else {
                    this.userRepositoryDB.save(userAD);
                }
            });
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

}
