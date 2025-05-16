package com.project.shopApp.common.exceptions;

public class CheckSizeImagesException extends RuntimeException {
    public CheckSizeImagesException() {
        super(String.format("File is too large! Maximum is 10MB"));
    }
}
