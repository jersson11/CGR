package com.test.testactivedirectory.infrastructure.security.Jwt.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.test.testactivedirectory.application.auth.dto.AuthResponseDto;
import com.test.testactivedirectory.application.role.dto.RoleDto;
import com.test.testactivedirectory.application.user.dto.UserDto;
import com.test.testactivedirectory.infrastructure.persistence.entity.RoleEntity;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {

    /**
     * Llave para cifrar el jwt
     */
    @Value("${jwt.secret.key}")
    private String secretKey;

    /**
     * Crea un nuevo jwt en base al cliente recibido por parametro y lo agrega a la
     * lista blanca
     * 
     * @param customerJwt Cliente a utilizar en la creacion del jwt
     * @return Jwt creado
     */
    public String createToken(AuthResponseDto customerJwt, List<RoleEntity> roles) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000 * 2); // 1 hora en milisegundos

        // String scope = customerJwt.getAuthorities().stream()
        // .map(GrantedAuthority::getAuthority)
        // .collect(Collectors.joining(" "));
        List<String> rolesClaim = roles.stream().map(RoleEntity::getName).toList();

        // customerJwt.getRoles().stream().map(RoleDto::getAuthority).collect(Collectors.joining("
        // "));
        // System.out.println("============Scoperoles================>" + scope);

        String tokenCreated = JWT.create()
                .withClaim("userName", customerJwt.getSAMAccountName())
                .withClaim("roles", rolesClaim)
                .withClaim("isEnabled", customerJwt.getIsEnable())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);

        return tokenCreated;
    }

    public String getClaimUserName(String token) {
        return JWT.decode(token).getClaim("userName").asString();
    }

    public boolean validateToken(String token) throws JsonProcessingException {
        // si se dispara una excepción, significa que el token no es válido
        if (isTokenExpired(token) != null)
            return true;

        if (!getUserDto(token).getIsEnable())
            return true;

        return false;

    }

    public AuthResponseDto getUserDto(String token) throws JsonProcessingException {

        String email = JWT.decode(token).getClaim("userName").asString();

        try {
            boolean isEnabled2 = JWT.decode(token).getClaim("isEnabled").asBoolean();
        } catch (Exception e) {
            System.out.println(e);
        }
        boolean isEnabled = JWT.decode(token).getClaim("isEnabled").asBoolean();

        // List<RoleDto> roles = Arrays.stream(rolesString.split(" "))
        // .map(roleAuthority -> new RoleDto(roleAuthority, isEnabled))
        // .collect(Collectors.toList());

        return AuthResponseDto.builder()
                .sAMAccountName(email)
                .isEnable(isEnabled)
                .build();
    }

    public List<String> getRolesToken(String token) {
        return getDecodedJWT(token).getClaim("roles").asList(String.class);
    }

    public String validateFirma(String token) {
        try {
            // Crear el algoritmo con la clave secreta
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            // Crear el verificador para validar la firma y la integridad del token
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            // Validar el token (si es inválido, lanzará una excepción)
            DecodedJWT jwt = verifier.verify(token);

            // Si el token es válido, devuelve null
            return null;
        } catch (TokenExpiredException e) {
            return "El token ha expirado";
        } catch (JWTVerificationException e) {
            return "Firma del token inválida o token corrupto";
        } catch (Exception e) {
            return "Error inesperado al validar el token".concat(e.getMessage());
        }
    }

    public String isTokenExpired(String token) {

        Date now = new Date();

        if (extractExpiration(token).before(now)) {
            Date expirationDate = extractExpiration(token);
            long timeUntilExpiration = expirationDate.getTime() - now.getTime();

            long hours = TimeUnit.MILLISECONDS.toHours(timeUntilExpiration);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(timeUntilExpiration) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(timeUntilExpiration) % 60;

            log.info(expirationDate.toString());

            return String.format("El token expiro en %d horas, %d minutos y %d segundos a las %s.", hours, minutes,
                    seconds,
                    expirationDate.toString());
        }

        return null;
    }

    private Date extractExpiration(String token) {

        System.out.println("Issu" + JWT.decode(token).getExpiresAt());

        return JWT.decode(token).getExpiresAt();
    }

    // Decodificar el token
    private DecodedJWT getDecodedJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

}