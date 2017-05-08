package com.indra.sofia2.archetype.exception;

public class RetryApiException extends Exception {

	private static final long serialVersionUID = -6678587645611028303L;

    public RetryApiException() {
    	super();
    }

    public RetryApiException(String message) {
       super(message);
    }
    
    public RetryApiException (Throwable cause) {
        super (cause);
    }
    
    public RetryApiException (String message, Throwable cause){
    	super(message, cause);
    }
}
