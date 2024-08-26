package org.github.mcmetricscollector.api.dto;

import lombok.Data;

@Data
public class MetricsDTO {

    private String serverName;
    private String serverType;

    private int onlinePlayers;

    private double cpuUsage;
    private double memoryUsage;

    private double[] tps;

}
