package com.meintrup.springcrud.controllers.rest;

/**
 * This class is an endpoint that queries another endpoint. Used to test Rest endpoint consumption from within spring.
 */
import com.meintrup.springcrud.proxies.PersonsProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/personproxy")
@RequiredArgsConstructor
public class PersonOpenFeignTestController {
    private final PersonsProxy personsProxy;

    @GetMapping("")
    @Operation(summary = "Get a list of all persons, but proxied from different source", responses = {
            @ApiResponse(responseCode = "200", description = "Okay", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"persons\"=[...]}"))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"message\"=\"Some sort fo erro message\"}"))
            ),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"message\"=\"Some sort fo erro message\"}"))
            ),
    })
    public ResponseEntity<String> getPersonsByProxy(
            @RequestHeader String authorization
    ) {
        return personsProxy.getProxiedPersons(
                authorization
        );
    }
}

