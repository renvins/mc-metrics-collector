package org.github.mcmetricscollector.database.sql.repository;

import org.github.mcmetricscollector.database.sql.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
