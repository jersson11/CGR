package com.test.testactivedirectory.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;
import com.test.testactivedirectory.infrastructure.persistence.repository.RoleRepositoryJpa;

@RestController()
@RequestMapping("/test")
public class Test {

    @Autowired
    RoleRepositoryJpa roleRepository;

    @GetMapping
    @Transactional
    public Map<String, Object> test() {
        RoleEntity role = new RoleEntity();
        role.setName("Nicolas");
        role.setDescription("mensaje 1");
        RoleEntity role2 = new RoleEntity();
        role2.setName("Santiago");
        role2.setDescription("mensaje 2");
        roleRepository.save(role);
        roleRepository.save(role2);

        Map<String, Object> json = new HashMap<>();
        json.put("roles", this.roleRepository.findAll());
        return json;
    }

}
