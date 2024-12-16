package com.cgr.base.infrastructure.externar.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cgr.base.domain.repository.IActiveDirectoryUserRepository;
import com.cgr.base.infrastructure.persistence.entity.UserEntity;
import com.unboundid.ldap.sdk.LDAPBindException;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

@Component
public class LDAPUsuarioRepository implements IActiveDirectoryUserRepository {

    @Override
    public Boolean checkAccount(String samAccountName, String password) {
        String ldapHost = "192.168.2.21";
        int ldapPort = 389;
        String baseDN = "OU=Tecser,OU=Usuarios,DC=tecser,DC=local";
        String domain = "tecser.local";

        try {
            String userPrincipalName = samAccountName + "@" + domain;

            LDAPConnection connection = new LDAPConnection(ldapHost, ldapPort);
            connection.bind(userPrincipalName, password);

            System.out.println("Autenticación exitosa para el usuario: " + userPrincipalName);

            SearchResultEntry usuario = buscarUsuarioPorSAMAccountName(connection, samAccountName, baseDN);

            if (usuario != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("DN: " + usuario.getDN());
                usuario.getAttributes().forEach(attr -> {
                    System.out.println(attr.getName() + ": " + String.join(", ", attr.getValues()));
                });
            }
            connection.close();

            return usuario != null;

        } catch (LDAPBindException e) {
            System.err.println("Error de autenticación: Usuario o contraseña incorrectos.");
        } catch (LDAPException e) {
            System.err.println("Error al conectar con el Directorio Activo:");
            e.printStackTrace();
        }

        return false;
    }

    private SearchResultEntry buscarUsuarioPorSAMAccountName(
            LDAPConnection connection, String samAccountName, String baseDN) throws LDAPException {
        String searchFilter = String.format("(sAMAccountName=%s)", samAccountName);
        SearchRequest searchRequest = new SearchRequest(baseDN, SearchScope.SUB, searchFilter);

        SearchResult searchResult = connection.search(searchRequest);

        if (searchResult.getEntryCount() == 0) {
            System.out.println("No se encontró ningún usuario con sAMAccountName: " + samAccountName);
            return null;
        }

        return searchResult.getSearchEntries().get(0);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        String ldapHost = "192.168.2.21";
        int ldapPort = 389;
        String baseDN = "OU=Tecser,OU=Usuarios,DC=tecser,DC=local";

        List<UserEntity> users = new ArrayList<>();

        try {
            // Conectar como una cuenta de servicio
            LDAPConnection connection = new LDAPConnection(ldapHost, ldapPort);

            String serviceUser = "cuipo.cgr@tecser.local";
            String servicePassword = "Colombia2024*";
            connection.bind(serviceUser, servicePassword);

            // Filtro para obtener solo objetos de usuario
            String searchFilter = "(objectClass=user)";
            SearchRequest searchRequest = new SearchRequest(baseDN, SearchScope.SUB, searchFilter);

            SearchResult searchResult = connection.search(searchRequest);

            for (SearchResultEntry entry : searchResult.getSearchEntries()) {
                UserEntity userEntity = new UserEntity();

                // Mapeo básico al modelo UserEntity
                userEntity.setSAMAccountName(entry.getAttributeValue("sAMAccountName"));
                userEntity.setFullName(entry.getAttributeValue("displayName"));
                userEntity.setEmail(entry.getAttributeValue("userPrincipalName"));
                userEntity.setEnabled(this.isEnabledUser(entry.getAttributeValue("userAccountControl")));
                userEntity.setPhone(entry.getAttributeValue("mobile"));
                userEntity.setCargo(entry.getAttributeValue("Title"));

                // Obtener y formatear la última modificación
                String whenChanged = entry.getAttributeValue("whenChanged");
                if (whenChanged != null) {
                    Date formattedDate = formatWhenChanged(whenChanged);
                    userEntity.setDateModify(formattedDate);
                }

                users.add(userEntity);
            }

            connection.close();

        } catch (LDAPException e) {
            System.err.println("Error al obtener todos los usuarios de Active Directory:");
            e.printStackTrace();
        }

        return users;
    }

    private Boolean isEnabledUser(String userAccountControl) {
        if (userAccountControl != null) {
            int uacValue = Integer.parseInt(userAccountControl);
            boolean isDisabled = (uacValue & 0x2) != 0; // Verificar si el bit está configurado
            return !isDisabled;
        } else {
            return false;
        }
    }

    private Date formatWhenChanged(String whenChanged) {
        try {
            // Formato original: yyyyMMddHHmmss.0Z
            SimpleDateFormat adFormat = new SimpleDateFormat("yyyyMMddHHmmss");

            // Remover ".0Z" del valor original
            if (whenChanged.contains(".")) {
                whenChanged = whenChanged.split("\\.")[0];
            }

            return adFormat.parse(whenChanged);

        } catch (ParseException e) {
            System.err.println("Error al formatear la fecha whenChanged: " + whenChanged);
            return null;
        }
    }
}
