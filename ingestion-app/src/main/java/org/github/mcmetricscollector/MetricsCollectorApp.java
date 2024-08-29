package org.github.mcmetricscollector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.mcmetricscollector.gen.model.MetricsDTO;
import org.github.mcmetricscollector.gen.model.ServerInfoDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MetricsCollectorApp {

    public static void main(String[] args) {
        SpringApplication.run(MetricsCollectorApp.class, args);
    }

    @Profile("mock")
    @Bean
    CommandLineRunner commandLineRunner(ObjectMapper objectMapper) {
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            var o = objectMapper;
            LocalDateTime ldt = LocalDateTime.of(2024, 8, 15, 0, 0, 0);
            SecureRandom random = new SecureRandom();
            List<MetricsDTO> metrics = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                MetricsDTO metricsDTO = new MetricsDTO();
                var millis = ldt.toInstant(ZoneOffset.UTC).toEpochMilli();
                metricsDTO.setTimestamp(millis);
                ldt = ldt.plusSeconds(20);
                ServerInfoDTO serverInfoDTO = new ServerInfoDTO();
                serverInfoDTO.setServerName("vanilla");
                serverInfoDTO.setServerType("survival");
                metricsDTO.setServerInfo(serverInfoDTO);
                int onlinePlayers = 100 + random.nextInt(101);
                metricsDTO.setOnlinePlayers(onlinePlayers);
                metricsDTO.setCpuUsage(BigDecimal.ZERO);
                metricsDTO.setMemoryUsage(BigDecimal.ZERO);

                double tps1m = 16 + random.nextDouble() * 4;
                List<BigDecimal> tps = new ArrayList<>();
                tps.add(BigDecimal.valueOf(Math.round(tps1m * 100.0) / 100.0));
                tps.add(BigDecimal.ZERO);
                tps.add(BigDecimal.ZERO);
                metricsDTO.setTps(tps);
                metrics.add(metricsDTO);
            }

            for (MetricsDTO metricsDTO : metrics) {
                restTemplate.postForObject("http://localhost:8080/v1/metrics", metricsDTO, MetricsDTO.class);
            }
        };
    }
}
