package me.plasmabase.pastimegames.exceptions;

public class EventListenerAlreadyBoundException extends Exception{
    public EventListenerAlreadyBoundException() {
        super();
    }
    public EventListenerAlreadyBoundException(String message) {
        super(message);
    }
    public EventListenerAlreadyBoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public EventListenerAlreadyBoundException(Throwable cause) {
        super(cause);
    }
    public EventListenerAlreadyBoundException(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
