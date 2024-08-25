package com.meintrup.springcrud.advice;

import com.meintrup.springcrud.entities.ErrorDetails;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * A class providing specific exception handlers for Rest controllers.
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ApiResponse(responseCode = "400", description = "Bad Request Illegal argument", content = @Content(
            schema = @Schema(implementation = ErrorDetails.class))
    )
    public ResponseEntity<ErrorDetails> exceptionIllegalArgumentHandler() {
        return ResponseEntity.badRequest()
                             .body(
                                     ErrorDetails.builder()
                                                 .message("The request was made with an illegal argument")
                                                 .build()
                             );
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ApiResponse(responseCode = "400", description = "Bad Request NoSuchElement", content = @Content(
            schema = @Schema(implementation = ErrorDetails.class))
    )
    public ResponseEntity<ErrorDetails> exceptionNoSuchElementHandler() {
        return ResponseEntity.badRequest()
                             .body(
                                     ErrorDetails.builder()
                                                 .message("The request element can not be found.")
                                                 .build()
                             );
    }

    @ExceptionHandler(ArithmeticException.class)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(
            schema = @Schema(implementation = ErrorDetails.class))
    )
    public ResponseEntity<ErrorDetails> exceptionArithmeticHandler() {
        return ResponseEntity.internalServerError()
                             .body(
                                     ErrorDetails.builder()
                                                 .message("An unexpected error happened on Server side")
                                                 .build()
                             );
    }
}
