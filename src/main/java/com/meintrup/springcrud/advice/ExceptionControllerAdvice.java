package com.meintrup.springcrud.advice;

import com.meintrup.springcrud.entities.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * A class providing specific exception handlers for Rest controllers.
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> exceptionIllegalArgumentHandler() {
        return ResponseEntity.badRequest()
                             .body(
                                     ErrorDetails.builder()
                                                 .message("The request was made with an illegal argument")
                                                 .build()
                             );
    }
}
