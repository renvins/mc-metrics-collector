package org.github.mcmetricscollector.api.service;

public interface Service {

    void load();
    default void unload() {}
}
