package com.acro.lab.ecommerce.exceptions;

public class ProductException extends RuntimeException {
    public ProductException(String message,Throwable cause){
        super(message,cause);
    }

}
