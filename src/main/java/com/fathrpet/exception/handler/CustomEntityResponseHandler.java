package com.fathrpet.exception.handler;

import com.fathrpet.exception.AlreadyListedException;
import com.fathrpet.exception.ExceptionResponse;
import com.fathrpet.exception.InsufficientFundsException;
import com.fathrpet.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Hidden
@ControllerAdvice
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFound(Exception exception, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyListedException.class)
    public final ResponseEntity<ExceptionResponse> handleNotAcceptable(Exception exception, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public final ResponseEntity<ExceptionResponse> handleInsufficientFunds(Exception exception, WebRequest request){
        ExceptionResponse response = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
