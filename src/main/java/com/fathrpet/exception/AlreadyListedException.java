package com.fathrpet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AlreadyListedException extends IllegalStateException{

    public AlreadyListedException(String message){
        super(message);
    }
}
