package com.project.shopApp.common.exceptions;

public class CheckLengthImagesException extends RuntimeException{
    public CheckLengthImagesException(){
        super(String.format("Number of images have to <= 5"));
    }
}
