package com.faust8888.cambridge.cqrs.exceptions.query;

public class NotFoundDocumentException extends Exception {

    private static final long serialVersionUID = 7718828512143293558L;

    private String message;
    private Throwable cause;

    public NotFoundDocumentException(final String message) {
        super(message);
        this.message = message;
    }

    public NotFoundDocumentException(final String message, final Throwable cause) {
        super(message, cause);
        this.cause = cause;
        this.message = message;
    }

}
