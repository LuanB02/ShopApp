package com.project.shopApp.common.exceptions;

public class CheckDuplicateException extends RuntimeException{
    public CheckDuplicateException(){
        super(String.format("Item is created"));
    }
}
