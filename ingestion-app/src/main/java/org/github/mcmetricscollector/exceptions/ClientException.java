package org.github.mcmetricscollector.exceptions;

public class ClientException extends RuntimeException {

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException() {
        super();
    }
}
