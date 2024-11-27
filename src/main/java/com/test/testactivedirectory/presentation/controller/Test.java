package com.test.testactivedirectory.presentation.controller;

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
    public void test() {
        RoleEntity role = new RoleEntity();
        role.setName("test1");
        System.out.println(roleRepository.save(role));
    }

}
