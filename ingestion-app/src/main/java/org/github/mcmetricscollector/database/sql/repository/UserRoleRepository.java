package org.github.mcmetricscollector.database.sql.repository;

import org.github.mcmetricscollector.database.sql.entities.UserRoleEntity;
import org.github.mcmetricscollector.database.sql.entities.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {
}
