package org.github.mcmetricscollector.service;

import org.bukkit.Bukkit;
import org.github.mcmetricscollector.MetricsCollectorLoader;
import org.github.mcmetricscollector.MetricsCollectorPlugin;
import org.github.mcmetricscollector.common.MetricsServiceImpl;
import org.github.mcmetricscollector.common.MetricsTask;

import java.io.IOException;

public class TaskServiceBukkit extends MetricsTask {

    private final MetricsCollectorPlugin plugin;

    public TaskServiceBukkit(MetricsCollectorPlugin plugin, MetricsServiceImpl metricsService) {
        super(metricsService);
        this.plugin = plugin;
    }

    @Override
    public void runTask(long seconds) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin,
                () -> {
                    try {
                        getMetricsService().sendMetrics(Bukkit.getOnlinePlayers().size());
                        MetricsCollectorLoader.LOGGER.info("Metrics sent :)");
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, 0L, 20 * seconds);
    }

    @Override
    public void runTask(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
