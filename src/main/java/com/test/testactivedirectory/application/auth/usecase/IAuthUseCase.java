package com.test.testactivedirectory.application.auth.usecase;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.testactivedirectory.application.auth.dto.AuthRequestDto;

import jakarta.servlet.http.HttpServletRequest;

public interface IAuthUseCase {

    Map<String, Object> signIn(AuthRequestDto userRequest,  HttpServletRequest servletRequest ) throws JsonProcessingException;
     
    Map<String, Object> authWithLDAPActiveDirectory (AuthRequestDto userRequest, HttpServletRequest servletRequest) throws JsonProcessingException;
    // String signOut(String jwt);

    // Map<String, Object> checkStatusUser(String jwt) throws JsonProcessingException ;
    
}


