package org.github.mcmetricscollector.common;

import lombok.RequiredArgsConstructor;
import org.github.mcmetricscollector.api.service.MetricsService;
import org.github.mcmetricscollector.api.service.TPSRetriever;

/* Common service to send metrics to Server app */
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final TPSRetriever tpsRetriever;

    @Override
    public void load() {

    }

    @Override
    public void sendMetrics() {
        /* Create MetricsDTO, JSON it and send through HTTP request */
    }
}
