package com.project.shopApp.common.exceptions;

import org.hibernate.annotations.Check;

public class CheckEmptyOrderException extends RuntimeException{
    public CheckEmptyOrderException(long id){
        super(String.format("Can not find order with id" + id));
    }
}
