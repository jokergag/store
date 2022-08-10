package com.cy.store.service.ex;

/**
 * @author joker
 * @create 2022-04-27
 */
public class AddressOverstepException extends ServiceException{

    public AddressOverstepException() {
        super();
    }

    public AddressOverstepException(String message) {
        super(message);
    }

    public AddressOverstepException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressOverstepException(Throwable cause) {
        super(cause);
    }

    protected AddressOverstepException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
