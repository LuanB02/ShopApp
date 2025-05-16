package com.project.shopApp.common.exceptions;


public class CheckEmptyCategoriesException extends RuntimeException{
    public CheckEmptyCategoriesException(){
        super(String.format("Can not find categories"));
    }
}