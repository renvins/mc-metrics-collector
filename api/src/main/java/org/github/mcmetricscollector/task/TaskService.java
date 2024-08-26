package org.github.mcmetricscollector.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.github.mcmetricscollector.MetricsService;

@Getter
@RequiredArgsConstructor
public abstract class TaskService {

    private final MetricsService metricsService;

    public abstract void runMetricsTask(long seconds); /* Method to start tracking metrics */
    public abstract void runAsync(Runnable runnable); /* Run an async task */

}
