package org.github.mcmetricscollector.data;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;

public class TPSRetrieverBukkit implements TPSRetriever {

    @Override
    public double[] retrieveTPS() {
        MinecraftServer minecraftServer = (MinecraftServer) Bukkit.getServer();
        return minecraftServer.recentTps;
    }
}
