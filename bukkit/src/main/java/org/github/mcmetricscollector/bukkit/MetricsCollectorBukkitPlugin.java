package org.github.mcmetricscollector.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public class MetricsCollectorBukkitPlugin extends JavaPlugin {

    private final MetricsCollectorBukkitLoader loader = new MetricsCollectorBukkitLoader(this);

    @Override
    public void onEnable() {
        loader.load();
    }

    @Override
    public void onDisable() {
        loader.unload();
    }
}
