package org.github.mcmetricscollector.common.config;

import lombok.Data;

@Data
public class RedisConfig {

    private final String host;
    private final int port;

    private final String username;
    private final String password;
}
