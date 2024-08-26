package org.github.mcmetricscollector;

import java.util.logging.Logger;

public class MetricsCollectorLoader implements Service {

    private final MetricsCollectorPlugin plugin;
    private static Logger LOGGER; /* Will not be null in any case (init onEnable) */

    public MetricsCollectorLoader(MetricsCollectorPlugin plugin) {
        this.plugin = plugin;
        LOGGER = plugin.getLogger();
    }

    @Override
    public void load() {
        LOGGER.info("Loading metrics collector...");
    }

    @Override
    public void unload() {
        LOGGER.info("Unloading metrics collector...");
    }
}
