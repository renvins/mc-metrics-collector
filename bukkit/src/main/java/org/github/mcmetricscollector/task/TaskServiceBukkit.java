package org.github.mcmetricscollector.task;

import lombok.RequiredArgsConstructor;
import org.github.mcmetricscollector.MetricsCollectorPlugin;

@RequiredArgsConstructor
public class TaskServiceBukkit implements TaskService {

    private final MetricsCollectorPlugin plugin;

    @Override
    public void runMetricsTask(Runnable runnable, long delay) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 0L, delay);
    }

    @Override
    public void runAsync(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
