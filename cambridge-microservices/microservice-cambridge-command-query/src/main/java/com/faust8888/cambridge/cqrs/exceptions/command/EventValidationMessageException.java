package com.faust8888.cambridge.cqrs.exceptions.command;

public class EventValidationMessageException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    private final String message;
    private final Throwable cause;

    public EventValidationMessageException(final String message, final Throwable cause) {
        super(message, cause);
        this.cause = cause;
        this.message = message;
    }

}
