package org.github.mcmetricscollector.service;

import org.github.mcmetricscollector.gen.model.MetricsDTO;

public interface MetricsService {


    /**
     * Saves the given metrics data.
     *
     * @param metricsDTO the data transfer object containing metrics information such as timestamp, server information,
     *                   online players count, CPU usage, memory usage, and TPS (ticks per second).
     */
    void saveMetrics(MetricsDTO metricsDTO);
}
