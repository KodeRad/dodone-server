package com.dodone.dodone.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@RestController
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    // Handles specific exceptions thrown by the controller
    // 1. Choose exception to handle (SQLIntegrityConstraintViolationException)
    @ExceptionHandler(ExceptionSQLIntegrityConstraintViolationException.class)
    // 2. Create method signature which returns response entity (params not required)

    public ResponseEntity<ErrorResponse> handleSqlIntegrityException
    () {

        // 3. Create error response that is returns when error occur
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
                "Attempt to insert or update data " +
                        "in a database violates database's defined integrity constraint.",
                LocalDateTime.now());

        // 4. Return ResponseEntity with response params
        return new ResponseEntity<>(response, response.getStatus());
    }


    @ExceptionHandler(value = ExceptionNoSuchElement.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException
            () {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
                "No such element found", LocalDateTime.now() );

        return new ResponseEntity<>(response, response.getStatus());
    }
}
