package com.cgr.base.presentation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract class AbstractController {

    protected ResponseEntity<?> processRequest(BindingResult result, Supplier<ResponseEntity<?>> action) {
        if (result.hasFieldErrors()) {
            return this.validation(result);
        }
        return action.get();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> errors.put(error.getField(),
                "El campo " + error.getField() + " " + error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
