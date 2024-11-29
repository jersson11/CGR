package com.test.testactivedirectory.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testactivedirectory.application.logs.usecase.LogUseCase;


@RestController
@RequestMapping("/api/v1/log")
public class LogController extends AbstractController {

    private final LogUseCase logService;

    public LogController(LogUseCase logService) {
        this.logService = logService;
    }

    @GetMapping
    public Map<String, Object> getLogAll() {
        Map<String, Object> json = new HashMap<>();
        json.put("logs", this.logService.logFindAll());
        return json;
    }

}
