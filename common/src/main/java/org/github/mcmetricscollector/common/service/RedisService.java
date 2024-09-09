package org.github.mcmetricscollector.common.service;

import org.github.mcmetricscollector.api.service.Service;
import redis.clients.jedis.JedisPubSub;

public interface RedisService extends Service {

    /**
     * Subscribes the specified handler to all message channels.
     *
     * @param handler the JedisPubSub handler to be subscribed for listening to channel messages
     */
    void subscribe(JedisPubSub handler);

    /**
     * Publishes a message to a Redis channel.
     *
     * @param message the message to be published
     */
    void publish(String message);

}
