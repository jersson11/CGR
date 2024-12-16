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
            return this.validate(result);
        }
        return action.get();
    }

    private ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> errors.put(error.getField(),
                "El campo " + error.getField() + " " + error.getDefaultMessage()));

        return this.requestResponse(errors, "error de validacion de json", HttpStatus.BAD_REQUEST, false);
    }

    protected ResponseEntity<?> requestResponse(Object data, String message) {
        return ResponseEntity.status(HttpStatus.OK).body(this.requestMap(data, message, HttpStatus.OK, true));
    }

    protected ResponseEntity<?> requestResponse(Object data, String message, HttpStatus status, boolean sucessful) {
        return ResponseEntity.status(status).body(this.requestMap(data, message, status, sucessful));
    }

    private Map<String, Object> requestMap(Object data, String message, HttpStatus status, boolean sucessful) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("status", status.value());
        response.put("sucessful", sucessful);
        response.put(sucessful ? "message" : "error", message);
        return response;
    }

}
