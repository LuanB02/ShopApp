package com.project.shopApp.common;

import com.project.shopApp.common.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(CheckEmptyItemsException.class)
    public ResponseEntity<ErrorMessage> handleItemNotFound(CheckEmptyItemsException itemNotFoundException) {
        ErrorMessage error = new ErrorMessage("Empty List", itemNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CheckEmptyItemException.class)
    public ResponseEntity<ErrorMessage> handleItemNotFoundById(CheckEmptyItemException itemNotFoundByIdException) {
        ErrorMessage error = new ErrorMessage("Not found", itemNotFoundByIdException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CheckDuplicateException.class)
    public ResponseEntity<ErrorMessage> handleItemDuplicated(CheckDuplicateException checkDuplicateException) {
        ErrorMessage error = new ErrorMessage("Item is duplicated", checkDuplicateException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(CheckSizeImagesException.class)
    public ResponseEntity<ErrorMessage> handleImagesSize(CheckSizeImagesException checkSizeImagesException) {
        ErrorMessage error = new ErrorMessage("Invalid size images", checkSizeImagesException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(CheckTypeOfImageException.class)
    public ResponseEntity<ErrorMessage> handleImagesType(CheckTypeOfImageException checkTypeOfImageException) {
        ErrorMessage error = new ErrorMessage("Invalid type images", checkTypeOfImageException.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(error);
    }
}
