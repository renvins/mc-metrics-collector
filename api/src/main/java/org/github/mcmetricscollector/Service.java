package org.github.mcmetricscollector;

public interface Service {

    void load();
    default void unload() {}
}
