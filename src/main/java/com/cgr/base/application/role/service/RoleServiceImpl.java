package com.cgr.base.application.role.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgr.base.application.role.dto.RoleRequestDto;
import com.cgr.base.application.role.usecase.IRoleService;
import com.cgr.base.domain.repository.IRoleRepository;
import com.cgr.base.infrastructure.persistence.entity.RoleEntity;
import com.cgr.base.infrastructure.utilities.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    private final DtoMapper dtoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<RoleRequestDto> findAll() {
        return this.dtoMapper.convertToListDto(this.roleRepository.findAll(), RoleRequestDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public RoleRequestDto findById(Long idRole) {
        return this.dtoMapper.convertToDto(this.roleRepository.findById(idRole), RoleRequestDto.class);
    }

    @Transactional
    @Override
    public RoleRequestDto create(RoleEntity roleEntity) {
        return this.dtoMapper.convertToDto(this.roleRepository.create(roleEntity), RoleRequestDto.class);
    }

    @Transactional
    @Override
    public RoleRequestDto update(RoleEntity roleEntity) {
        return this.dtoMapper.convertToDto(this.roleRepository.update(roleEntity), RoleRequestDto.class);
    }

    @Transactional
    @Override
    public RoleRequestDto activateOrDeactivate(Long idRole) {
        return this.dtoMapper.convertToDto(this.roleRepository.activateOrDeactivate(idRole), RoleRequestDto.class);
    }

}
