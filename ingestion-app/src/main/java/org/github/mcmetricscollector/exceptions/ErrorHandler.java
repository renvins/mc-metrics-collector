package org.github.mcmetricscollector.exceptions;

import org.github.mcmetricscollector.dto.ErrorMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleClientException(ClientException e) {
        return createErrorMessage(400, e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleServiceException(ServiceException e) {
        return createErrorMessage(500, e.getMessage());
    }

    @ExceptionHandler(UserNotEnabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleUserNotEnabledException(UserNotEnabledException e) {
        return createErrorMessage(403, e.getMessage());
    }

    @NotNull
    private static ErrorMessage createErrorMessage(int code, String message) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(code);
        errorMessage.setDescription(message);
        return errorMessage;
    }
    
}
