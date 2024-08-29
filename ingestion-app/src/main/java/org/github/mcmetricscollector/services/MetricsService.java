package org.github.mcmetricscollector.services;

import org.github.mcmetricscollector.gen.model.MetricsDTO;

public interface MetricsService {


    void saveMetrics(MetricsDTO metricsDTO);

}
