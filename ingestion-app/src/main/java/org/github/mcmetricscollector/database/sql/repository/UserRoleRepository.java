package org.github.mcmetricscollector.database.sql.repository;

import org.github.mcmetricscollector.database.sql.entities.UserRoleEntity;
import org.github.mcmetricscollector.database.sql.entities.UserRoleId;
import org.github.mcmetricscollector.security.objects.RoleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {

    @Query("""
            SELECT new org.github.mcmetricscollector.security.objects.RoleProjection(re.name, pe.name)
            FROM RoleEntity re, UserEntity ue, PermissionEntity pe, UserRoleEntity ure, RolePermissionEntity rpe
            WHERE ue.username = :username
            AND re = rpe.role
            AND pe = rpe.permission
            AND ure.role = re
            AND ure.user = ue
            """)
    List<RoleProjection> findRolesOf(@Param("username") String username);

}
