package com.project.shopApp.common.exceptions;

public class CheckEmptyUserException extends RuntimeException{
    public CheckEmptyUserException(){
        super(String.format("Can not find any user"));
    }
}
