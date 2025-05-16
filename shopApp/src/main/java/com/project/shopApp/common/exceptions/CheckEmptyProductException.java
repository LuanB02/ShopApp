package com.project.shopApp.common.exceptions;

public class CheckEmptyProductException extends RuntimeException{
    public CheckEmptyProductException(Long id){
        super(String.format("Can not find product with id " + id));
    }
}
