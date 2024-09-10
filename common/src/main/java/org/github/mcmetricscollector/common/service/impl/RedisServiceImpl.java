package org.github.mcmetricscollector.common.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.mcmetricscollector.common.config.RedisConfig;
import org.github.mcmetricscollector.common.service.RedisService;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;

@RequiredArgsConstructor
@Slf4j
public class RedisServiceImpl implements RedisService {

    private final RedisConfig config;
    @Getter private JedisPooled pool;

    @Override
    public void load() {
        log.info("Loading redis service...");

        ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();

        poolConfig.setBlockWhenExhausted(true); // Waits for a connection to become available
        poolConfig.setTestWhileIdle(true); // Test connection health periodically while idling

        pool = new JedisPooled(poolConfig, config.getHost(), config.getPort(), config.getUsername(), config.getPassword());
    }

    @Override
    public void unload() {
        log.info("Unloading redis service...");
        if (pool != null) {
            pool.close();
        }
    }

    @Override
    public void subscribe(JedisPubSub handler) {
        pool.subscribe(handler, "metrics-alerts");
    }

    @Override
    public void publish(String message) {
        try {
            pool.publish("metrics-alerts", message);
        } catch (JedisException e) {
            log.error("Can not publish redis message!", e);
        }
    }
}
