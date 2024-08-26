package org.github.mcmetricscollector.task;

import org.github.mcmetricscollector.MetricsCollectorPlugin;
import org.github.mcmetricscollector.common.MetricsServiceImpl;
import org.github.mcmetricscollector.common.MetricsTask;

public class TaskServiceBukkit extends MetricsTask {

    private final MetricsCollectorPlugin plugin;

    public TaskServiceBukkit(MetricsCollectorPlugin plugin, MetricsServiceImpl metricsService) {
        super(metricsService);
        this.plugin = plugin;
    }

    @Override
    public void runTask(long seconds) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> getMetricsService().load(), 0L, 20 * seconds);
    }

    @Override
    public void runTask(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
