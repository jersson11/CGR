package com.test.testactivedirectory.infrastructure.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.test.testactivedirectory.infrastructure.exception.component.AccessDeniedHandlerException;
import com.test.testactivedirectory.infrastructure.security.Jwt.filters.JwtAuthFilter;
import com.test.testactivedirectory.infrastructure.security.helper.RoleUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AccessDeniedHandlerException accessDeniedHandlerException;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // RequestMatcher authMatcher = new MvcRequestMatcher(new
        // HandlerMappingIntrospector(), "/auth/**");

        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .exceptionHandling(t -> t.accessDeniedHandler(accessDeniedHandlerException))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**", "/api/v1/auth/**", "/auth**").permitAll();
                    auth.requestMatchers("/test").permitAll();
                    auth.requestMatchers("/api/v1/menu/**").permitAll();
                    auth.requestMatchers("/api/v1/log/**").permitAll();
                    auth.requestMatchers("/api/v1/role/**").permitAll();
                    auth.requestMatchers("/api/v1/user/**").permitAll();
                    auth.requestMatchers("/admin/**").hasAnyRole(RoleUtil.ADMIN, RoleUtil.FUNCIONARIO,
                            RoleUtil.Usuario);
                    auth.requestMatchers("/user/**").hasAnyRole(RoleUtil.FUNCIONARIO,
                            RoleUtil.ADMIN, RoleUtil.Usuario);
                    auth.anyRequest().authenticated();
                });

        // http.exceptionHandling(exceptions -> exceptions
        // .authenticationEntryPoint(customAuthenticationEntryPoint)
        // .accessDeniedHandler(customAccessDeniedHandler));

        http.headers(headers -> headers
                .httpStrictTransportSecurity(hsts -> hsts
                        .includeSubDomains(true)
                        .maxAgeInSeconds(31536000)));

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(@NonNull HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:4200",
                        "http://localhost:5173/", "http://192.168.0.220/",
                        "http://localhost:8000/", "http://localhost:8000",
                        "http://localhost:48496", "https://665922d5497f3aaadbaaf8b0--melodic-halva-c4b1b1.netlify.app/",
                        "https://665922d5497f3aaadbaaf8b0--melodic-halva-c4b1b1.netlify.app",
                        "https://bovid.site/", "https://bovid.site", "http://bovid.site/",
                        "http://bovid.site", "https://strong-toffee-1046b5.netlify.app/",
                        "https://strong-toffee-1046b5.netlify.app"));
                config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
                config.setAllowedHeaders(Arrays.asList("*"));
                config.setAllowCredentials(true);
                config.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));
                config.setMaxAge(3600L);
                return config;
            }
        };
    }
}
