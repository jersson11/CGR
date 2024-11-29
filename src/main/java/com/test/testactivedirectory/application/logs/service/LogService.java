package com.test.testactivedirectory.application.logs.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.logs.dto.LogDto;
import com.test.testactivedirectory.application.logs.usecase.LogUseCase;
import com.test.testactivedirectory.domain.repository.ILogRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.LogEntity;
import com.test.testactivedirectory.infrastructure.utilities.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService implements LogUseCase {

    private final ILogRepository adapterLogRepository;

    private final DtoMapper dtoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LogDto> logFindAll() {
        return this.dtoMapper.convertToListDto(this.adapterLogRepository.logFindAll(), LogDto.class);
    }

    @Override
    @Transactional
    public LogEntity createLog(String userName) {
        LogEntity logEntity = new LogEntity("n/a", new Date(), true, userName);
        return this.adapterLogRepository.createLog(logEntity, userName);
    }

}
