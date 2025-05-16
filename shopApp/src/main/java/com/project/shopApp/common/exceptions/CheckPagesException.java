package com.project.shopApp.common.exceptions;

public class CheckPagesException extends RuntimeException{
    public CheckPagesException(){
        super(String.format("Page is invalid"));
    }
}
