package org.github.mcmetricscollector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/* Data model */
@RequiredArgsConstructor @Getter
public class Metrics {

    private final String serverName;
    private final String serverType;

    private final int onlinePlayers;

    private final double cpuUsage;
    private final double memoryUsage;

    /* Will prob change data type */
    private final double[] tps;
}
