package org.github.mcmetricscollector;

import org.bukkit.plugin.java.JavaPlugin;

public class MetricsCollectorPlugin extends JavaPlugin {

    private final MetricsCollectorLoader loader = new MetricsCollectorLoader(this);

    @Override
    public void onEnable() {
        loader.load();
    }

    @Override
    public void onDisable() {
        loader.unload();
    }
}
