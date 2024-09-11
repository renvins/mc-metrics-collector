package org.github.mcmetricscollector.security;

import org.github.mcmetricscollector.security.objects.Role;

import java.util.List;

public interface Principal {

    String identifier();

    List<Role> roles();

    record PrincipalImpl(String identifier, List<Role> roles) implements Principal {

    }

}
