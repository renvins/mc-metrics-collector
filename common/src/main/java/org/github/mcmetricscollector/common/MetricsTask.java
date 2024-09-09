package org.github.mcmetricscollector.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.github.mcmetricscollector.common.service.impl.MetricsServiceImpl;

@Getter
@RequiredArgsConstructor
public abstract class MetricsTask {

    private final MetricsServiceImpl metricsService;

    /**
     * Run the metrics task every x seconds
     * @param seconds The amount of seconds to wait before running the task
     */
    public abstract void runTask(long seconds);

    public abstract void runTask(Runnable runnable);

}
