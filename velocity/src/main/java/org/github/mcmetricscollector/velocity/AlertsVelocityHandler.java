package org.github.mcmetricscollector.velocity;

import com.google.gson.Gson;
import redis.clients.jedis.JedisPubSub;

public class AlertsVelocityHandler extends JedisPubSub {

    private final Gson gson = new Gson();

    @Override
    public void onMessage(String channel, String message) {
        // TODO: Send alerts to all players with mcmetricscollector.alerts permission
    }
}
