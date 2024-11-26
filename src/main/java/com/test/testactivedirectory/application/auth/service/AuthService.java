package com.test.testactivedirectory.application.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.testactivedirectory.application.auth.dto.AuthRequestDto;
import com.test.testactivedirectory.application.auth.mapper.UserMapper;
import com.test.testactivedirectory.application.auth.usecase.IAuthUseCase;
import com.test.testactivedirectory.application.auth.usecase.UserUseCase;
import com.test.testactivedirectory.domain.models.UserModel;
import com.test.testactivedirectory.domain.repository.IUserRepository;
import com.test.testactivedirectory.infrastructure.security.Jwt.providers.JwtAuthenticationProvider;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService implements IAuthUseCase {

    private final IUserRepository userRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public AuthService(IUserRepository userRepository, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.userRepository = userRepository;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Override
    public Map<String, Object> signIn(AuthRequestDto userRequest, HttpServletRequest servletRequest)
            throws JsonProcessingException {

        Map<String, Object> response = new HashMap<>();

        try {

            UserModel userModel = userRepository.findBySAMAccountName(userRequest.getSAMAccountName());

            if (userModel != null && userModel.getPassword().equals(userRequest.getPassword())) {

                AuthRequestDto userRequestDto = UserMapper.INSTANCE.toUserRequestDto(userModel);

                String token = jwtAuthenticationProvider.createToken(userDto);

                response.put("user", userRequestDto);
                response.put("message", "User authenticated successfully");
                response.put("statusCode", 200);
                response.put("status", "success");
                return response;

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;

    }

}
