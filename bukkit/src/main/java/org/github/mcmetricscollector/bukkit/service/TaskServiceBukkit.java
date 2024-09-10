package org.github.mcmetricscollector.bukkit.service;

import org.bukkit.Bukkit;
import org.github.mcmetricscollector.bukkit.MetricsCollectorBukkitLoader;
import org.github.mcmetricscollector.bukkit.MetricsCollectorBukkitPlugin;
import org.github.mcmetricscollector.common.service.impl.MetricsServiceImpl;
import org.github.mcmetricscollector.common.MetricsTask;

import java.io.IOException;

public class TaskServiceBukkit extends MetricsTask {

    private final MetricsCollectorBukkitPlugin plugin;

    public TaskServiceBukkit(MetricsCollectorBukkitPlugin plugin, MetricsServiceImpl metricsService) {
        super(metricsService);
        this.plugin = plugin;
    }

    @Override
    public void runTask(long seconds) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin,
                () -> {
                    try {
                        getMetricsService().sendMetrics(Bukkit.getOnlinePlayers().size());
                        MetricsCollectorBukkitLoader.LOGGER.info("Metrics sent :)");
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
