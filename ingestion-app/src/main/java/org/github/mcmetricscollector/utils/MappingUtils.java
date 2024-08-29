package org.github.mcmetricscollector.utils;

import org.github.mcmetricscollector.database.influx.points.MetricsPoint;
import org.github.mcmetricscollector.gen.model.MetricsDTO;

import java.time.Instant;

public final class MappingUtils {

    private MappingUtils() {

    }

    public static MetricsPoint mapToMetricsPoint(MetricsDTO metricsDTO) {
        MetricsPoint metricsPoint = new MetricsPoint();
        Instant timestamp = Instant.ofEpochMilli(metricsDTO.getTimestamp());
        metricsPoint.setTimestamp(timestamp);
        metricsPoint.setServerName(metricsDTO.getServerInfo().getServerName());
        metricsPoint.setServerType(metricsDTO.getServerInfo().getServerType());
        metricsPoint.setOnlinePlayers(metricsDTO.getOnlinePlayers());
        metricsPoint.setCpuUsage(metricsDTO.getCpuUsage().doubleValue());
        metricsPoint.setMemoryUsage(metricsDTO.getMemoryUsage().doubleValue());
        metricsPoint.setTps1(metricsDTO.getTps().get(0).doubleValue());
        metricsPoint.setTps5(metricsDTO.getTps().get(1).doubleValue());
        metricsPoint.setTps15(metricsDTO.getTps().get(2).doubleValue());
        return metricsPoint;
    }

}
