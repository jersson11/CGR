package com.test.testactivedirectory.infrastructure.externar.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.testactivedirectory.domain.repository.IActiveDirectoryUserRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;
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
                System.out.println("full name: " + entry.getAttributeValue("displayName"));
                System.out.println("email: " + entry.getAttributeValue("userPrincipalName"));
                System.out.println("teléfono: " + entry.getAttributeValue("mobile"));
                System.out.println("---------------------------------------------------------");

                users.add(userEntity);
            }

            connection.close();

        } catch (LDAPException e) {
            System.err.println("Error al obtener todos los usuarios de Active Directory:");
            e.printStackTrace();
        }

        return users;
    }
}
