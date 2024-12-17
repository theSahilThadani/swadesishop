package com.swadesishop.backend.exceptions;

public class ProductNotFoundException extends Exception{

    //creates object of ProductNotFoundException class and setting message using constructor.
    public ProductNotFoundException(String message){
        super(message);
    }
}
