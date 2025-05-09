package com.project.shopApp.common.exceptions;

public class CheckEmptyItemException extends RuntimeException{
    public CheckEmptyItemException(Long id){
        super(String.format("Can not find item with id " + id));
    }
}
