package com.project.shopApp.common.exceptions;

public class CheckEmptyProductsException extends RuntimeException{
    public CheckEmptyProductsException(){
        super(String.format("Do not find products"));
    }
}
