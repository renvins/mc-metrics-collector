package org.github.mcmetricscollector.api.service;

import java.io.IOException;

public interface MetricsService extends Service {

    /* This param because it depends on the implementation */
    void sendMetrics(int playersOnline) throws IOException, InterruptedException;

}
