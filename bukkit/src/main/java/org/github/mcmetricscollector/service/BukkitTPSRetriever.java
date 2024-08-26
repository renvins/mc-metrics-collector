package org.github.mcmetricscollector.service;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.github.mcmetricscollector.api.service.TPSRetriever;

public class BukkitTPSRetriever implements TPSRetriever {

    @Override
    public double[] retrieveTPS() {
        MinecraftServer minecraftServer = (MinecraftServer) Bukkit.getServer();
        return minecraftServer.recentTps;
    }
}
