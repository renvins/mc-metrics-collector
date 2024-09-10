package org.github.mcmetricscollector.bukkit.service;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.github.mcmetricscollector.api.service.TPSRetriever;

public class TPSRetrieverBukkit implements TPSRetriever {

    @Override
    public double[] retrieveTPS() {
        MinecraftServer minecraftServer = (MinecraftServer) Bukkit.getServer();
        return minecraftServer.recentTps;
    }
}
