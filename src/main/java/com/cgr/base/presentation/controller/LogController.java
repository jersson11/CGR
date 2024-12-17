package com.cgr.base.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgr.base.application.logs.usecase.ILogUseCase;


@RestController
@RequestMapping("/api/v1/log")
public class LogController extends AbstractController {

    private final ILogUseCase logService;

    public LogController(ILogUseCase logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<?> getLogAll() {
        return requestResponse(this.logService.logFindAll(), "logs de inicio de sesión");
    }

}
