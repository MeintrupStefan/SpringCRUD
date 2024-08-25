package com.meintrup.springcrud.controllers.rest;

import com.meintrup.springcrud.entities.Furniture;
import com.meintrup.springcrud.repositories.FurnitureRepository;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/furniture")
@RequiredArgsConstructor
public class FurnitureController {
    private final FurnitureRepository furnitureRepository;

    @PostMapping("")
    @Operation(summary = "Endpoint to add a furniture to the db", responses = {
            @ApiResponse(responseCode = "200", description = "Okay", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = ""))
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
    public void storeFurniture(@RequestBody Furniture furniture) {
        furnitureRepository.storeFurniture(furniture);
    }

    @GetMapping("")
    @Operation(summary = "Returns a list of all furnitures inside the db.", responses = {
            @ApiResponse(responseCode = "200", description = "Okay", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = ""))
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
    public List<Furniture> findFurniture() {
        return furnitureRepository.findAllPurchases();
    }
}
