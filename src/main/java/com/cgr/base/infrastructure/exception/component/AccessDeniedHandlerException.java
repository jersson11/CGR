package com.cgr.base.infrastructure.exception.component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerException implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Configura la respuesta HTTP para enviar un mensaje de error personalizado al
        // front-end
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Acceso denegado: No tienes permisos para acceder a este recurso.");
        response.getWriter().flush();
    }
}