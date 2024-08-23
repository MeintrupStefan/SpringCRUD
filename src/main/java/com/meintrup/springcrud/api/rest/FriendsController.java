package com.meintrup.springcrud.api.rest;

import com.meintrup.springcrud.util.JsonFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@Slf4j
public class FriendsController {
    private final JsonFactory jsonFactory;

    @Autowired
    public FriendsController(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    @GetMapping("/friends/of")
    @Operation(summary = "Returns a list of names that the specific user is friends with", responses = {
            @ApiResponse(responseCode = "200", description = "Okay", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "[\"Peter\", \"John\"]"))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    public ResponseEntity<String> getFriends(@RequestParam String userId) {
        log.debug("Requesting friends of {}", userId);
        UUID userOfInterestUUID;
        try {
            // Check for valid input params.
            userOfInterestUUID = UUID.fromString(userId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // TODO: Implement authentication
        // TODO: Get data from repository / db
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Timmy");
        friends.add("Julia");
        friends.add("Peter");
        return new ResponseEntity<>(
                jsonFactory.toJson(friends),
                HttpStatus.OK
        );
    }
}
