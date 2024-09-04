package org.github.mcmetricscollector.database.sql.repository;

import org.github.mcmetricscollector.database.sql.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
