package org.github.mcmetricscollector.bukkit;

import org.github.mcmetricscollector.api.dto.ServerInfoDTO;
import org.github.mcmetricscollector.api.service.Service;
import org.github.mcmetricscollector.api.service.TPSRetriever;
import org.github.mcmetricscollector.common.service.impl.MetricsServiceImpl;
import org.github.mcmetricscollector.common.MetricsTask;
import org.github.mcmetricscollector.bukkit.service.TPSRetrieverBukkit;
import org.github.mcmetricscollector.bukkit.service.TaskServiceBukkit;

import java.util.logging.Logger;

public class MetricsCollectorBukkitLoader implements Service {

    private final MetricsCollectorBukkitPlugin plugin;
    public static Logger LOGGER; /* Will not be null in any case (init onEnable) */

    private MetricsTask taskService;

    public MetricsCollectorBukkitLoader(MetricsCollectorBukkitPlugin plugin) {
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
