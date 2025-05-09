package com.project.shopApp.common.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private String errorCode;
    private String message;
}

