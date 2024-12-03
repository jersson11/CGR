package com.test.testactivedirectory.infrastructure.externar.repository;

import org.springframework.stereotype.Component;

import com.test.testactivedirectory.domain.repository.IActiveDirectoryUserRepository;
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
}
