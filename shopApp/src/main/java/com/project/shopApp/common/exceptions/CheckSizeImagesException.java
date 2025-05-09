package com.project.shopApp.common.exceptions;

public class CheckSizeImagesException extends RuntimeException{
    public CheckSizeImagesException(){
        super(String.format("Number of images have to <= 5"));
    }
}
