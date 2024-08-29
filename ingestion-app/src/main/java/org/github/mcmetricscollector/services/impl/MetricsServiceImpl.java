package org.github.mcmetricscollector.services.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import lombok.extern.slf4j.Slf4j;
import org.github.mcmetricscollector.gen.model.MetricsDTO;
import org.github.mcmetricscollector.services.MetricsService;
import org.github.mcmetricscollector.utils.MappingUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MetricsServiceImpl implements MetricsService {

    private final InfluxDBClient influxDBClient;

    public MetricsServiceImpl(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }

    @Override
    public void saveMetrics(MetricsDTO metricsDTO) {
        var metricsPoint = MappingUtils.mapToMetricsPoint(metricsDTO);
        log.info("Saving metrics: {}", metricsPoint);
        influxDBClient.getWriteApiBlocking().writeMeasurement(WritePrecision.S, metricsPoint);
        log.info("Metrics saved successfully!");
    }
}
