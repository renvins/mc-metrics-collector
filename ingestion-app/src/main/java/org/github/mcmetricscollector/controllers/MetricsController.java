package org.github.mcmetricscollector.controllers;

import org.github.mcmetricscollector.gen.api.MetricsApi;
import org.github.mcmetricscollector.gen.model.MetricsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController implements MetricsApi {

    @Override
    public ResponseEntity<Void> receiveMetrics(MetricsDTO metricsDTO) {
        return null;
    }
}
