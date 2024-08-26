package org.github.mcmetricscollector.api.service;

import org.github.mcmetricscollector.api.dto.MetricsDTO;

public interface MetricsService extends Service {

    void sendMetrics(MetricsDTO metrics);

}
