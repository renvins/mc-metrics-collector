package org.github.mcmetricscollector.velocity;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.github.mcmetricscollector.common.config.RedisConfig;
import org.github.mcmetricscollector.common.service.RedisService;
import org.github.mcmetricscollector.common.service.impl.RedisServiceImpl;
import org.slf4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(id = "mc-metrics-collector", name = "MCMetricsCollector",
        version = "1.0", description = "Tracks MC server's metrics and displays them by graphs",
        authors = {"renvins, Valxrie"})
public class MetricsCollectorVelocityPlugin {

    private final ProxyServer server;
    private final Logger logger;

    private final CommentedConfigurationNode root;
    private final RedisService redisService;

    @Inject
    public MetricsCollectorVelocityPlugin(ProxyServer server, Logger logger, @DataDirectory Path path) {
        this.server = server;
        this.logger = logger;

        Path configPath = path.resolve("config.yml");
        this.root = getRootNode(configPath);

        if (root == null) {
            server.shutdown();
        }
        CommentedConfigurationNode redisNode = root.node("redis");
        RedisConfig config = getConfig(redisNode);

        this.redisService = new RedisServiceImpl(config);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("Loading MCMetricsCollector...");

        redisService.load();
        redisService.subscribe(new AlertsVelocityHandler());
    }

    private CommentedConfigurationNode getRootNode(Path configPath) {
        YamlConfigurationLoader loader = YamlConfigurationLoader.builder().path(configPath).build();
        try {
            return loader.load();
        } catch (IOException e) {
            logger.error("An error occurred while loading this configuration", e);
            return null;
        }

    }

    private RedisConfig getConfig(CommentedConfigurationNode node) {
        String host = node.node("host").getString();
        int port = node.node("port").getInt();

        String username = node.node("username").getString();
        String password = node.node("password").getString();

        return new RedisConfig(host, port, username, password);
    }
}
