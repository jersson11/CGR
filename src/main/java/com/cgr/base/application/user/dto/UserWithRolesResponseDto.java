package com.cgr.base.application.user.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cgr.base.application.role.dto.RoleRequestDto;
import com.cgr.base.infrastructure.persistence.entity.RoleEntity;

import lombok.Data;

@Data
public class UserWithRolesResponseDto {

    private Long idUser;

    private String userName;

    private String fullName;

    private String email;

    private String phone;

    private boolean enabled;

    private Date dateModify;

    private String cargo;

    private List<RoleRequestDto> roles = new ArrayList<>();

    public void addRole(List<RoleEntity> rolesEntity) {
        rolesEntity.forEach(role -> {
            var role2 = new RoleRequestDto();
            role2.setId(role.getId());
            role2.setName(role.getName());
            role2.setDescription(role.getDescription());
            role2.setEnable(role.isEnable());
            roles.add(role2);
        });

    }
}
