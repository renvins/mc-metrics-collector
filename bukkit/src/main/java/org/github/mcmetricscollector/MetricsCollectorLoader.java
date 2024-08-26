package org.github.mcmetricscollector;

import org.github.mcmetricscollector.data.TPSRetrieverBukkit;
import org.github.mcmetricscollector.task.TaskService;
import org.github.mcmetricscollector.task.TaskServiceBukkit;

import java.util.logging.Logger;

public class MetricsCollectorLoader implements Service {

    private final MetricsCollectorPlugin plugin;
    private static Logger LOGGER; /* Will not be null in any case (init onEnable) */

    private final MetricsService metricsService;
    private final TaskService taskService;

    public MetricsCollectorLoader(MetricsCollectorPlugin plugin) {
        this.plugin = plugin;
        LOGGER = plugin.getLogger();

        this.metricsService = new MetricsService(new TPSRetrieverBukkit());
        this.taskService = new TaskServiceBukkit(plugin, metricsService);
    }

    @Override
    public void load() {
        LOGGER.info("Loading metrics collector...");
        plugin.saveDefaultConfig();

        taskService.runMetricsTask(plugin.getConfig().getLong("metricsFrequency"));
    }

    @Override
    public void unload() {
        LOGGER.info("Unloading metrics collector...");
    }
}
