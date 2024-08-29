package org.github.mcmetricscollector;

import org.github.mcmetricscollector.api.dto.ServerInfoDTO;
import org.github.mcmetricscollector.api.service.Service;
import org.github.mcmetricscollector.api.service.TPSRetriever;
import org.github.mcmetricscollector.common.MetricsServiceImpl;
import org.github.mcmetricscollector.common.MetricsTask;
import org.github.mcmetricscollector.service.TPSRetrieverBukkit;
import org.github.mcmetricscollector.service.TaskServiceBukkit;

import java.util.logging.Logger;

public class MetricsCollectorLoader implements Service {

    private final MetricsCollectorPlugin plugin;
    public static Logger LOGGER; /* Will not be null in any case (init onEnable) */

    private MetricsTask taskService;

    public MetricsCollectorLoader(MetricsCollectorPlugin plugin) {
        this.plugin = plugin;
        LOGGER = plugin.getLogger();
    }

    @Override
    public void load() {
        LOGGER.info("Loading metrics collector...");
        plugin.saveDefaultConfig();

        taskService = initTaskService();
        taskService.runTask(plugin.getConfig().getLong("metricsFrequency"));
    }

    @Override
    public void unload() {
        LOGGER.info("Unloading metrics collector...");
    }

    private TaskServiceBukkit initTaskService() {
        TPSRetriever tpsRetriever = new TPSRetrieverBukkit();
        ServerInfoDTO serverInfoDTO = new ServerInfoDTO(
                plugin.getConfig().getString("serverName"),
                plugin.getConfig().getString("serverType"));

        long timeout = plugin.getConfig().getLong("timeout");

        MetricsServiceImpl metricsService = new MetricsServiceImpl(serverInfoDTO, tpsRetriever, timeout);
        return new TaskServiceBukkit(plugin, metricsService);
    }
}
