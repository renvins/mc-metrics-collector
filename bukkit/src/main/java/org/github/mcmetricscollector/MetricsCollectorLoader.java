package org.github.mcmetricscollector;

import org.github.mcmetricscollector.api.service.Service;
import org.github.mcmetricscollector.common.MetricsServiceImpl;
import org.github.mcmetricscollector.common.MetricsTask;
import org.github.mcmetricscollector.service.BukkitTPSRetriever;
import org.github.mcmetricscollector.task.TaskServiceBukkit;

import java.util.logging.Logger;

public class MetricsCollectorLoader implements Service {

    private final MetricsCollectorPlugin plugin;
    private static Logger LOGGER; /* Will not be null in any case (init onEnable) */

    private final MetricsTask taskService;

    public MetricsCollectorLoader(MetricsCollectorPlugin plugin) {
        this.plugin = plugin;
        LOGGER = plugin.getLogger();

        /* TODO: Retrieve serverName and serverType from config */
        MetricsServiceImpl metricsService = new MetricsServiceImpl();
        this.taskService = new TaskServiceBukkit(plugin, metricsService);
    }

    @Override
    public void load() {
        LOGGER.info("Loading metrics collector...");
        plugin.saveDefaultConfig();

        taskService.runTask(plugin.getConfig().getLong("metricsFrequency"));
    }

    @Override
    public void unload() {
        LOGGER.info("Unloading metrics collector...");
    }
}
