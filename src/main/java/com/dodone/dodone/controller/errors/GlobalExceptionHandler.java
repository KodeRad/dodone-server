package com.dodone.dodone.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
@RestController
@ControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    // Handles specific exceptions thrown by the controller
    // 1. Choose exception to handle (ExceptionNoSuchElement)
    @ExceptionHandler(value = ExceptionNoSuchElement.class)
    // 2. Create method signature which returns response entity (params not required)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException
    () {
        // 3. Create error response that is returns when error occur
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
                "No such element found", LocalDateTime.now() );

        // 4. Return ResponseEntity with response params
        return new ResponseEntity<>(response, response.getStatus());
    }


    @ExceptionHandler(ExceptionSQLIntegrityConstraintViolation.class)

    public ResponseEntity<ErrorResponse> handleSqlIntegrityException
    () {

        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
                "Attempt to insert or update datain a database " +
                        "violates database's defined integrity constraint.",
                LocalDateTime.now());

        return new ResponseEntity<>(response, response.getStatus());
    }



    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException
    () {
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error reading the file.",
                LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());

    } @ExceptionHandler(value = ParseException.class)
    public ResponseEntity<ErrorResponse> handleParserException
    () {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST,
                "Error parsing iCalendar file",
                LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
