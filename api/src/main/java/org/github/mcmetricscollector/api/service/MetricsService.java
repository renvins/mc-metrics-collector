package org.github.mcmetricscollector.api.service;

public interface MetricsService extends Service {

    /* This param because it depends on the implementation */
    void sendMetrics(int playersOnline);

}
