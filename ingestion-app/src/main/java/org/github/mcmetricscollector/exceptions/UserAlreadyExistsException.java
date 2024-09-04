package org.github.mcmetricscollector.exceptions;

public class UserAlreadyExistsException extends ClientException {

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
