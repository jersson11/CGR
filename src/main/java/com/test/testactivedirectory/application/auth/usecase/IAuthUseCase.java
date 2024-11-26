package com.test.testactivedirectory.application.usecase;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.testactivedirectory.application.dto.RegistrationRequestDto;
import com.test.testactivedirectory.application.dto.UserRequestDto;

import jakarta.servlet.http.HttpServletRequest;

public interface IAuthUseCase {

    Map<String, Object> signIn(UserRequestDto userRequest,  HttpServletRequest servletRequest ) throws JsonProcessingException;

    // String signOut(String jwt);

    // Map<String, Object> checkStatusUser(String jwt) throws JsonProcessingException ;
    
}
