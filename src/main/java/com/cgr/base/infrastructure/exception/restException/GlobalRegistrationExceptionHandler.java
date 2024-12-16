package com.cgr.base.infrastructure.exception.restException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.cgr.base.infrastructure.exception.customException.InvalidVerificationTokenException;
import com.cgr.base.infrastructure.exception.customException.ResourceNotFoundException;
import com.cgr.base.presentation.controller.AbstractController;

@RestControllerAdvice
public class GlobalRegistrationExceptionHandler extends AbstractController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidVerificationTokenException.class)
    public Map<String, String> tokenNotFound(InvalidVerificationTokenException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    @ExceptionHandler({ ResourceNotFoundException.class, NoHandlerFoundException.class })
    ResponseEntity<?> notFound(Exception ex) {
        return this.throwErrorMessage(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    ResponseEntity<?> throwBadRequest(Exception ex) {
        return this.throwErrorMessage(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> throwErrorMessage(Exception ex, HttpStatus httpStatus) {
        return requestResponse(null, ex.getMessage(), httpStatus, false);
    }

}
