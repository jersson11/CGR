package com.test.testactivedirectory.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testactivedirectory.domain.repository.IActiveDirectoryUserRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.role.RoleRepositoryJpa;

@RestController()
@RequestMapping("/test")
public class Test {

    @Autowired
    IActiveDirectoryUserRepository usersAD;

    @GetMapping
    @Transactional
    public Map<String, Object> test() {
        Map<String, Object> json = new HashMap<>();
        json.put("usuariosAD", usersAD.getAllUsers());
        return json;
    }

}
