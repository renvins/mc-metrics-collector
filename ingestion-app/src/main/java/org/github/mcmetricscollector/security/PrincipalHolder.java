package org.github.mcmetricscollector.security;

public class PrincipalHolder {

    private final ThreadLocal<Principal> container = new ThreadLocal<>();

    private PrincipalHolder() {
    }

    public static PrincipalHolder getInstance() {
        return PrincipalHolderHelper.INSTANCE;
    }

    public Principal getPrincipal() {
        return container.get();
    }

    public void setPrincipal(Principal principal) {
        this.container.set(principal);
    }

    public void clear() {
        container.remove();
    }

    private static class PrincipalHolderHelper {

        static final PrincipalHolder INSTANCE = new PrincipalHolder();

    }

}
