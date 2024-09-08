package org.github.mcmetricscollector.controllers;

import org.github.mcmetricscollector.gen.api.AlertsApi;
import org.github.mcmetricscollector.gen.model.AlertsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertsController implements AlertsApi {

    @Override
    public ResponseEntity<Void> receiveAlerts(AlertsDTO alertsDTO) {
        // TODO: Send alerts through metrics
        return ResponseEntity.ok().build();
    }
}
