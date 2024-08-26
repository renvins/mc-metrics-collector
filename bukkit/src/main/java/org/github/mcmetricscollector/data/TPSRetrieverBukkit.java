package org.github.mcmetricscollector.data;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;

public class TPSRetrieverBukkit implements TPSRetriever {

    @Override
    public double[] retrieveTPS() {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();

        return new double[0];
    }
}
