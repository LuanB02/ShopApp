package com.project.shopApp.common.exceptions;

public class CheckTypeOfImageException extends RuntimeException{
    public CheckTypeOfImageException(){
        super(String.format("File must be an image"));
    }
}
