package com.test.testactivedirectory.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableAspectJAutoProxy
// @ComponentScan(basePackages = "com.alcadia.bovid.Component")
@Configuration
public class ApplicationConfig {

    /**
     * Bean de Password Encoder para inyeccion
     * 
     * @return Implemetación BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new ObjectMapper();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // @Bean
    // public JwtAuthFilter jwtAuthFilter() {
    // return new JwtAuthFilter(jwtAuthenticationProvider);
    // }
}
