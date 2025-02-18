package com.luv2code.demo.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    // Add exception handling Code here
    // Add an Exception Handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){

        // Create StudentErrorResponse
        System.out.println("here.....");
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }

    // Add another Exception handler - to catch any exceptions (catch all)

    @ExceptionHandler
    ResponseEntity<StudentErrorResponse>  handleException(Exception exc) {

        // Create StudentErrorResponse
        System.out.println("tehre.....");
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
