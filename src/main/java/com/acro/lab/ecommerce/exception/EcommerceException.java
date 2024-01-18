package com.acro.lab.ecommerce.exception;

public class EcommerceException extends RuntimeException{

    public EcommerceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EcommerceException(String message) {
        super(message);
    }

}
