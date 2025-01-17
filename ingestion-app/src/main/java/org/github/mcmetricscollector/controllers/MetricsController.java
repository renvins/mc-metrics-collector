package org.github.mcmetricscollector.controllers;

import lombok.extern.slf4j.Slf4j;
import org.github.mcmetricscollector.gen.api.MetricsAPI;
import org.github.mcmetricscollector.gen.model.MetricsDTO;
import org.github.mcmetricscollector.security.RequiresPermission;
import org.github.mcmetricscollector.service.MetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MetricsController implements MetricsAPI {

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    @RequiresPermission(RequiresPermission.PUSH_PERMISSION)
    public ResponseEntity<Void> receiveMetrics(MetricsDTO metricsDTO) {
        if (metricsDTO.getTps().size() != 3) {
            log.error("Invalid metrics received: {}. TPS array size must be 3...", metricsDTO);
            return ResponseEntity.badRequest().build();
        }
        metricsService.saveMetrics(metricsDTO);
        return ResponseEntity.ok().build();
    }
}
