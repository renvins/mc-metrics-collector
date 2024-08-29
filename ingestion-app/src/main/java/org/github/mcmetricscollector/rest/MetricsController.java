package org.github.mcmetricscollector.rest;

import org.github.mcmetricscollector.api.dto.MetricsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetricsController {

    /* TODO: Filter check for metrics */

    @GetMapping("/v1/metrics")
    public List<MetricsDTO> getMetrics() {

        /* TODO: InfluxDB implementation */

        return null;
    }

    @PostMapping("/v1/metrics")
    public ResponseEntity<MetricsDTO> sendMetrics(@RequestBody MetricsDTO metricsDTO) {

        /* TODO: InfluxDB implementation */

        return ResponseEntity.created(null).build();
    }
}
