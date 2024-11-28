package com.test.testactivedirectory.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testactivedirectory.application.user.dto.UserWithRolesRequestDto;
import com.test.testactivedirectory.application.user.usecase.UserUseCase;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController {

    private UserUseCase userService;

    public UserController(UserUseCase userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<String, Object> getAll() {
        Map<String, Object> json = new HashMap<>();
        json.put("usuarios", this.userService.findAll());
        return json;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserWithRolesRequestDto rolesRequestDto) {
        // return this.processRequest(result,
        // () ->
        // ResponseEntity.ok(this.userService.assignRolesToUser(rolesRequestDto)));
        return ResponseEntity.ok(this.userService.assignRolesToUser(rolesRequestDto));
    }

}
