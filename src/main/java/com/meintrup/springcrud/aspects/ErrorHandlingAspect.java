package com.meintrup.springcrud.aspects;

import com.meintrup.springcrud.entities.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * An aspect that handles errors in api calls.
 */
@Aspect
@Slf4j
public class ErrorHandlingAspect {

    /**
     * Log unhandled exceptions in REST endpoints & return error status code.
     *
     * @return The api endpoint result or Error Response on exception
     */
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) "
            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
    )
    public Object handleRestEndpointException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed(); // Execute the controller method
        } catch (Throwable throwable) {
            log.error("Error occurred in controller method: {}", joinPoint.getSignature()
                                                                          .getName(), throwable);
            ErrorDetails errorDetails = ErrorDetails.builder()
                                                    .message("An unexpected error happened on the server")
                                                    .build();
            return ResponseEntity.internalServerError()
                                 .body(errorDetails);
        }
    }
}
