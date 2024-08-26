package org.github.mcmetricscollector.task;

public interface TaskService {

    void runMetricsTask(Runnable runnable, long delay); /* Method to start tracking metrics */
    void runAsync(Runnable runnable); /* Run an async task */

}
