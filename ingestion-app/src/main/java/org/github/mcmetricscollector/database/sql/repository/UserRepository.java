package org.github.mcmetricscollector.database.sql.repository;

import org.github.mcmetricscollector.database.sql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUsersByUsername(String username);
}
