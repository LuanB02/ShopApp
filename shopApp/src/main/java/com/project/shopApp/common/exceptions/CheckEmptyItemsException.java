package com.project.shopApp.common.exceptions;

public class CheckEmptyItemsException extends RuntimeException{
    public CheckEmptyItemsException(){
        super(String.format("Do not find items"));
    }
}
