package com.cgr.base.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgr.base.application.user.dto.UserWithRolesRequestDto;
import com.cgr.base.application.user.usecase.UserSynchronizerUseCase;
import com.cgr.base.application.user.usecase.UserUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController {

    private UserUseCase userService;

    private UserSynchronizerUseCase synchronizerUsers;

    public UserController(UserUseCase userService, UserSynchronizerUseCase synchronizerUsers) {
        this.userService = userService;
        this.synchronizerUsers = synchronizerUsers;
    }

    @GetMapping
    public Map<String, Object> getAll() {
        Map<String, Object> json = new HashMap<>();
        json.put("usuarios", this.userService.findAll());
        return json;
    }

    @PostMapping
    public ResponseEntity<?> assignRole(@Valid @RequestBody UserWithRolesRequestDto rolesRequestDto,
            BindingResult result) {
        return this.processRequest(result,
                () -> ResponseEntity.ok(this.userService.assignRolesToUser(rolesRequestDto)));
    }

    @GetMapping("/synchronize")
    public Map<String, Object> synchronizeAD() {
        Map<String, Object> json = new HashMap<>();
        json.put("sincronizacion", this.synchronizerUsers.synchronizeUsers());
        return json;
    }

}
