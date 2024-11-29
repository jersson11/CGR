package com.test.testactivedirectory.infrastructure.exception.restException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.test.testactivedirectory.infrastructure.exception.customException.InvalidVerificationTokenException;
import com.test.testactivedirectory.infrastructure.exception.customException.ResourceNotFoundException;
import com.test.testactivedirectory.infrastructure.exception.dto.ErrorDto;

@RestControllerAdvice
public class GlobalRegistrationExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidVerificationTokenException.class)
    public Map<String, String> tokenNotFound(InvalidVerificationTokenException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }

    @ExceptionHandler({ ResourceNotFoundException.class, NoHandlerFoundException.class })
    ResponseEntity<ErrorDto> notFound(Exception ex) {
        return this.throwErrorMessage(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    ResponseEntity<ErrorDto> throwBadRequest(Exception ex) {
        return this.throwErrorMessage(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorDto> throwErrorMessage(Exception ex, HttpStatus httpStatus) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(),
                ex.getClass().toString(),
                httpStatus.value(),
                new Date());
        return ResponseEntity.status(httpStatus).body(errorDto);
    }

}
