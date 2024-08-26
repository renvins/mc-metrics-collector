package org.github.mcmetricscollector.task;

import org.github.mcmetricscollector.MetricsCollectorPlugin;
import org.github.mcmetricscollector.MetricsService;

public class TaskServiceBukkit extends TaskService {

    private final MetricsCollectorPlugin plugin;

    public TaskServiceBukkit(MetricsCollectorPlugin plugin, MetricsService metricsService) {
        super(metricsService);
        this.plugin = plugin;
    }

    @Override
    public void runMetricsTask(long seconds) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> getMetricsService().load(), 0L, 20 * seconds);
    }

    @Override
    public void runAsync(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
