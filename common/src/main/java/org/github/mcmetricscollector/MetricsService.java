package org.github.mcmetricscollector;

import lombok.RequiredArgsConstructor;
import org.github.mcmetricscollector.data.TPSRetriever;

/* Common service to send metrics to Server app */
@RequiredArgsConstructor
public class MetricsService implements Service {

    private final TPSRetriever tpsRetriever;

    @Override
    public void load() {

    }
}
