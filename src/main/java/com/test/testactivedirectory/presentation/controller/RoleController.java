package com.test.testactivedirectory.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testactivedirectory.application.role.interfaces.RoleService;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController extends AbstractController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public Map<String, Object> getAll() {
        Map<String, Object> json = new HashMap<>();
        json.put("roles", this.roleService.findAll());
        return json;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.roleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RoleEntity role, BindingResult result) {
        return this.processRequest(result, () -> ResponseEntity.ok(this.roleService.create(role)));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody RoleEntity role, BindingResult result) {
        return this.processRequest(result, () -> ResponseEntity.ok(this.roleService.update(role)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        RoleEntity role = this.roleService.activateOrDeactivate(id);
        return ResponseEntity.ok(role);
    }

}
