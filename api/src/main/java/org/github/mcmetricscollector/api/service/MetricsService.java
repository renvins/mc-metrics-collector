package org.github.mcmetricscollector.api.service;

import java.io.IOException;

public interface MetricsService extends Service {


    /**
     * Sends metrics data including online players, CPU usage, memory usage, and TPS to the metrics ingestion application.
     *
     * @param playersOnline The number of players currently online on the server.
     * @throws IOException If an I/O error occurs when sending metrics data.
     * @throws InterruptedException If the thread is interrupted while sending metrics data.
     */
    void sendMetrics(int playersOnline) throws IOException, InterruptedException;

}
