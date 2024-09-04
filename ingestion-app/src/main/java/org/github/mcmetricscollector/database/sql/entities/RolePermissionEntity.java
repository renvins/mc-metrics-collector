package org.github.mcmetricscollector.database.sql.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "role_permissions")
public class RolePermissionEntity {

    @EmbeddedId
    private RolePermissionId id;

    @MapsId("roleId")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @MapsId("permissionId")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionEntity permission;

}
