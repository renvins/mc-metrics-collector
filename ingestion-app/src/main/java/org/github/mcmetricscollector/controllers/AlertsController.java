package org.github.mcmetricscollector.controllers;

import com.google.gson.Gson;
import org.github.mcmetricscollector.common.service.RedisService;
import org.github.mcmetricscollector.gen.api.AlertsAPI;
import org.github.mcmetricscollector.gen.model.AlertsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertsController implements AlertsAPI {

    private final RedisService redisService;
    private final Gson gson;

    public AlertsController(RedisService redisService) {
        this.redisService = redisService;
        this.gson = new Gson();
    }

    @Override
    public ResponseEntity<Void> receiveAlerts(AlertsDTO alertsDTO) {
        String json = gson.toJson(alertsDTO);

        redisService.publish(json);
        return ResponseEntity.ok().build();
    }
}
