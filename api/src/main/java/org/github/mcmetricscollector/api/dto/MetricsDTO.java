package org.github.mcmetricscollector.api.dto;

import lombok.Data;

@Data
public class MetricsDTO {

    private ServerInfoDTO serverInfo;

    private int onlinePlayers;

    private double cpuUsage;
    private double memoryUsage;

    private double[] tps;

    public MetricsDTO(ServerInfoDTO serverInfo, int onlinePlayers, double cpuUsage, double memoryUsage, double[] tps) {
        this.serverInfo = serverInfo;
        this.onlinePlayers = onlinePlayers;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.tps = tps;
    }
}
