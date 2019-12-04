package me.sup2is.board.exception;

public class ReplyHasChildException extends Exception {

    public ReplyHasChildException() {
    }

    public ReplyHasChildException(String message) {
        super(message);
    }

    public ReplyHasChildException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReplyHasChildException(Throwable cause) {
        super(cause);
    }

    public ReplyHasChildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
