package com.test.testactivedirectory.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.domain.repository.ILogRepository;
import com.test.testactivedirectory.infrastructure.exception.customException.ResourceNotFoundException;
import com.test.testactivedirectory.infrastructure.persistence.entity.LogEntity;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.logs.LogsRepositoryJpa;
import com.test.testactivedirectory.infrastructure.persistence.repository.user.UserRepositoryJpa;

@Component
public class LogRepositoryAdapterImpl implements ILogRepository {

    private final LogsRepositoryJpa logRepositoryJpa;

    private final UserRepositoryJpa userRepositoryJpa;

    public LogRepositoryAdapterImpl(LogsRepositoryJpa logRepositoryJpa, UserRepositoryJpa userRepositoryJpa) {
        this.logRepositoryJpa = logRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Transactional(readOnly = true)
    @Override
    public List<LogEntity> logFindAll() {
        return this.logRepositoryJpa.findAll();
    }

    @Transactional
    @Override
    public LogEntity createLog(LogEntity logEntity, String userName) {
        Optional<UserEntity> userEntityOptional = this.userRepositoryJpa.findBySAMAccountName(userName);
        if (userEntityOptional.isPresent()) {
            logEntity.setUser(userEntityOptional.get());
            LogEntity log = this.logRepositoryJpa.save(logEntity);
            return log;
        } else {
            throw new ResourceNotFoundException("el usuario con nombre=" + userName + " no existe");
        }
    }

}
