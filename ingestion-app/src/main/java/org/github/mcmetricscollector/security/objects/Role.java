package org.github.mcmetricscollector.security.objects;

import lombok.Data;

import java.util.List;

@Data
public class Role {

    private String name;

    private List<String> permissions;

    public Role(String name, List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}
