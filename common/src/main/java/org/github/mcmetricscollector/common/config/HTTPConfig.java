package org.github.mcmetricscollector.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/* Config object for HTTP */
@RequiredArgsConstructor
@Getter
public class HTTPConfig {

    private final int timeout;
    private final int retry;

    /* See how to setup authorization */
}
