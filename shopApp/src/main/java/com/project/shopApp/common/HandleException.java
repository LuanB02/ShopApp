package com.project.shopApp.common;

import com.project.shopApp.common.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(CheckEmptyProductsException.class)
    public ResponseEntity<ErrorMessage> handleItemNotFound(CheckEmptyProductsException itemNotFoundException) {
        ErrorMessage error = new ErrorMessage("Empty List", itemNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CheckEmptyCategoriesException.class)
    public ResponseEntity<ErrorMessage> handleCategoriesNotFound(CheckEmptyCategoriesException itemNotFoundException) {
        ErrorMessage error = new ErrorMessage("Empty List", itemNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CheckEmptyCategoryException.class)
    public ResponseEntity<ErrorMessage> handleCategoryNotFound(CheckEmptyCategoryException itemNotFoundException) {
        ErrorMessage error = new ErrorMessage("Not found", itemNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CheckEmptyProductException.class)
    public ResponseEntity<ErrorMessage> handleItemNotFoundById(CheckEmptyProductException itemNotFoundByIdException) {
        ErrorMessage error = new ErrorMessage("Not found", itemNotFoundByIdException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CheckDuplicateException.class)
    public ResponseEntity<ErrorMessage> handleItemDuplicated(CheckDuplicateException checkDuplicateException) {
        ErrorMessage error = new ErrorMessage("Item is duplicated", checkDuplicateException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(CheckLengthImagesException.class)
    public ResponseEntity<ErrorMessage> handleImagesSize(CheckLengthImagesException checkLengthImagesException) {
        ErrorMessage error = new ErrorMessage("Invalid size images", checkLengthImagesException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(CheckTypeOfImageException.class)
    public ResponseEntity<ErrorMessage> handleImagesType(CheckTypeOfImageException checkTypeOfImageException) {
        ErrorMessage error = new ErrorMessage("Invalid type images", checkTypeOfImageException.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(error);
    }
    @ExceptionHandler(CheckSizeImagesException.class)
    public ResponseEntity<ErrorMessage> handleImagesSize(CheckSizeImagesException checkSizeImagesException) {
        ErrorMessage error = new ErrorMessage("Invalid size images", checkSizeImagesException.getMessage());
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(error);
    }
    @ExceptionHandler(CheckPagesException.class)
    public ResponseEntity<ErrorMessage> handlePageSize(CheckPagesException checkPagesException) {
        ErrorMessage error = new ErrorMessage("Invalid page size", checkPagesException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(CheckEmptyUserException.class)
    public ResponseEntity<ErrorMessage> handleEmptyUser(CheckEmptyUserException checkEmptyUserException) {
        ErrorMessage error = new ErrorMessage("Empty user", checkEmptyUserException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(CheckEmptyOrderException.class)
    public ResponseEntity<ErrorMessage> handleEmptyOrder(CheckEmptyOrderException checkEmptyOrderException) {
        ErrorMessage error = new ErrorMessage("Empty order", checkEmptyOrderException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
