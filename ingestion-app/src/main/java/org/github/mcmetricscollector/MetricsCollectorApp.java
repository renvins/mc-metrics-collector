package org.github.mcmetricscollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.influxdb.spring")
public class MetricsCollectorApp {

    public static void main(String[] args) {
        SpringApplication.run(MetricsCollectorApp.class, args);
    }
}
