package org.github.mcmetricscollector.common.service.impl;

import com.google.gson.Gson;
import com.sun.management.OperatingSystemMXBean;
import org.github.mcmetricscollector.api.dto.MetricsDTO;
import org.github.mcmetricscollector.api.dto.ServerInfoDTO;
import org.github.mcmetricscollector.api.service.MetricsService;
import org.github.mcmetricscollector.api.service.TPSRetriever;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Base64;

public class MetricsServiceImpl implements MetricsService {

    private final ServerInfoDTO serverInfo;
    private final TPSRetriever tpsRetriever;

    private final MemoryMXBean memory;
    private final OperatingSystemMXBean cpu;

    private final Gson gson;

    private final HttpClient client;
    private final long timeout;

    private final DecimalFormat df;


    public MetricsServiceImpl(ServerInfoDTO serverInfo, TPSRetriever tpsRetriever, long timeout) {
        this.serverInfo = serverInfo;
        this.tpsRetriever = tpsRetriever;

        this.memory = ManagementFactory.getMemoryMXBean();
        this.cpu = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        this.gson = new Gson();

        this.client = HttpClient.newHttpClient();
        this.timeout = timeout;

        this.df = new DecimalFormat("#.##");
    }

    @Override
    public void load() {
    }

    @Override
    public void sendMetrics(int onlinePlayers) throws IOException, InterruptedException {
        double cpuUsage = Double.parseDouble(df.format(cpu.getCpuLoad() * 100));
        double memoryUsage = (double) memory.getHeapMemoryUsage().getUsed() / 1073741824;

        double[] tps = tpsRetriever.retrieveTPS();

        MetricsDTO dto = new MetricsDTO(serverInfo, onlinePlayers, cpuUsage, memoryUsage, tps);
        String metricsJson = gson.toJson(dto);

        String token = Base64.getEncoder().encodeToString("admin:admin".getBytes());
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(metricsJson);

        URI uri = URI.create("http://localhost:8080/metrics");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Basic " + token)
                .timeout(Duration.ofSeconds(timeout))
                .POST(body)
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
