package org.github.mcmetricscollector.api.dto;

import lombok.Data;

@Data
public class ServerInfoDTO {

    private String serverName;
    private String serverType;

    public ServerInfoDTO() {}

    public ServerInfoDTO(String serverName, String serverType) {
        this.serverName = serverName;
        this.serverType = serverType;
    }
}
