package org.github.mcmetricscollector.common;

import com.sun.management.OperatingSystemMXBean;
import org.github.mcmetricscollector.api.dto.MetricsDTO;
import org.github.mcmetricscollector.api.dto.ServerInfoDTO;
import org.github.mcmetricscollector.api.service.MetricsService;
import org.github.mcmetricscollector.api.service.TPSRetriever;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.DecimalFormat;

/* Common service to send metrics to ingestion app */
public class MetricsServiceImpl implements MetricsService {

    private final ServerInfoDTO serverInfo;
    private final TPSRetriever tpsRetriever;

    private final MemoryMXBean memory;
    private final OperatingSystemMXBean cpu;


    public MetricsServiceImpl(ServerInfoDTO serverInfo, TPSRetriever tpsRetriever) {
        this.serverInfo = serverInfo;
        this.tpsRetriever = tpsRetriever;

        this.memory = ManagementFactory.getMemoryMXBean();
        this.cpu = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    }

    @Override
    public void load() {

    }

    @Override
    public void sendMetrics(int onlinePlayers) {
        DecimalFormat df = new DecimalFormat("#.##");

        double cpuUsage = Double.parseDouble(df.format(cpu.getCpuLoad() * 100));
        double memoryUsage = (double) memory.getHeapMemoryUsage().getUsed() / 1073741824;

        double[] tps = tpsRetriever.retrieveTPS();

        MetricsDTO dto = new MetricsDTO(serverInfo, onlinePlayers, cpuUsage, memoryUsage, tps);

        /* TODO: Serialize with GSON and send HTTP request */
    }
}
