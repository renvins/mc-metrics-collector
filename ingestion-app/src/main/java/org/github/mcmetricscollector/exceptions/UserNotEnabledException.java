package org.github.mcmetricscollector.exceptions;

public class UserNotEnabledException extends ClientException {

    public UserNotEnabledException() {
        super();
    }

    public UserNotEnabledException(Throwable cause) {
        super(cause);
    }

    public UserNotEnabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotEnabledException(String message) {
        super(message);
    }
}
