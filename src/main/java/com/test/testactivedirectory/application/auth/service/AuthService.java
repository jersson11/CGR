package com.test.testactivedirectory.application.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.testactivedirectory.application.auth.dto.AuthRequestDto;
import com.test.testactivedirectory.application.auth.dto.AuthResponseDto;
import com.test.testactivedirectory.application.auth.mapper.AuthMapper;
import com.test.testactivedirectory.application.auth.usecase.IAuthUseCase;
import com.test.testactivedirectory.domain.models.UserModel;
import com.test.testactivedirectory.domain.repository.IActiveDirectoryUserRepository;
import com.test.testactivedirectory.domain.repository.IUserRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;
import com.test.testactivedirectory.infrastructure.persistence.entity.Menu.Menu;
import com.test.testactivedirectory.infrastructure.persistence.repository.user.UserRepositoryJpa;
import com.test.testactivedirectory.infrastructure.security.Jwt.providers.JwtAuthenticationProvider;
import com.test.testactivedirectory.application.logs.usecase.LogUseCase;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthUseCase {

    private final IUserRepository userRepository;

    private final UserRepositoryJpa userRepositoryFull;

    private final IActiveDirectoryUserRepository activeDirectoryUserRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final LogUseCase logService;

    @Transactional
    @Override
    public Map<String, Object> signIn(AuthRequestDto userRequest, HttpServletRequest servletRequest)
            throws JsonProcessingException {

        Map<String, Object> response = new HashMap<>();

        try {

            UserModel userModel = userRepository.findBySAMAccountName(userRequest.getSAMAccountName());

            System.err.println("userModel: " + userModel);
            if (userModel != null && userModel.getPassword().equals(userRequest.getPassword())) {

                Optional<UserEntity> userOptional = this.userRepositoryFull
                        .findBySAMAccountName(userRequest.getSAMAccountName());
                AuthResponseDto userDto = AuthMapper.INSTANCE.toAuthResponDto(userModel);

                String token = jwtAuthenticationProvider.createToken(userDto, userOptional.get().getRoles());

                userDto.setToken(token);
                userDto.setIsEnable(true);

                response.put("user", userDto);
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

    @Transactional
    @Override
    public Map<String, Object> authWithLDAPActiveDirectory(AuthRequestDto userRequest,
            HttpServletRequest servletRequest)
            throws JsonProcessingException {

        Map<String, Object> response = new HashMap<>();

        try {

            Boolean isAccountValid = activeDirectoryUserRepository.checkAccount(
                    userRequest.getSAMAccountName(),
                    userRequest.getPassword());

            if (isAccountValid) {

                UserEntity user = this.userRepositoryFull
                        .findBySAMAccountNameWithRoles(userRequest.getSAMAccountName()).get();
                AuthResponseDto userRequestDto = AuthMapper.INSTANCE.toAuthResponDto(userRequest);

                userRequestDto.setRoles(user.getRoles().stream().map(RoleEntity::getName).toList());

                String token = jwtAuthenticationProvider.createToken(userRequestDto, user.getRoles());

                List<Menu> menus = this.userRepositoryFull
                        .findMenusByRoleNames(user.getRoles().stream().map(RoleEntity::getName).toList());

                userRequestDto.setMenus(menus);

                userRequestDto.setToken(token);
                userRequestDto.setIsEnable(true);

                this.logService.createLog(userRequest.getSAMAccountName());

                response.put("user", userRequestDto);
                response.put("message", "User authenticated successfully");
                response.put("statusCode", 200);
                response.put("status", "success");
                return response;
            }

        } catch (Exception e) {
            System.err.println("Error en la capa de aplicaciontion en service: " + e.getMessage());
        }
        response.put("message", "User not authenticated");
        return response;
    }

}
