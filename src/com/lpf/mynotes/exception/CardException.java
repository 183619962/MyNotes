package com.lpf.mynotes.exception;

public class CardException extends Exception {
	
	private String mErrorCode;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructs a new {@code Exception} with the current stack trace and the
     * specified detail message.
     *
     * @param detailMessage
     *            the detail message for this exception.
     */
    public CardException(String errorCode, String detailMessage) {
        super(detailMessage);
        this.mErrorCode = errorCode;
    }
    
    public String getErrorCode() {
    	return mErrorCode;
    }

}
