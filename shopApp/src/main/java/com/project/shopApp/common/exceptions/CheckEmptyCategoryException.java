package com.project.shopApp.common.exceptions;


public class CheckEmptyCategoryException extends RuntimeException{
    public CheckEmptyCategoryException(Long id){
        super(String.format("Can not find category with id " + id));
    }
}