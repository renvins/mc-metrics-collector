package org.github.mcmetricscollector.controllers;

import lombok.extern.slf4j.Slf4j;
import org.github.mcmetricscollector.gen.api.MetricsApi;
import org.github.mcmetricscollector.gen.model.MetricsDTO;
import org.github.mcmetricscollector.services.MetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MetricsController implements MetricsApi {

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public ResponseEntity<Void> receiveMetrics(MetricsDTO metricsDTO) {
        if (metricsDTO.getTps().size() != 3) {
            log.error("Invalid metrics received: {}. TPS array size must be 3...", metricsDTO);
            return ResponseEntity.badRequest().build();
        }
        metricsService.saveMetrics(metricsDTO);
        return ResponseEntity.ok().build();
    }
}
