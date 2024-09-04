package org.github.mcmetricscollector.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.github.mcmetricscollector.database.sql.entities.PermissionEntity}
 */
@Data
public class PermissionEntityDto implements Serializable {

    private Long id;
    private String name;

}
