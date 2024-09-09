package org.github.mcmetricscollector.api.service;

public interface TPSRetriever {


    /**
     * Retrieves the current server TPS (Ticks Per Second).
     *
     * @return an array of doubles representing the current TPS readings.
     */
    double[] retrieveTPS(); /* Don't need to do that async */
}
