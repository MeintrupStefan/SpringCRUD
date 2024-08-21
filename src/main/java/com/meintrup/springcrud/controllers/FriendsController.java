package com.meintrup.springcrud.controllers;

import com.meintrup.springcrud.util.GsonJsonFactory;
import com.meintrup.springcrud.util.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FriendsController {
    private final JsonFactory jsonFactory;
    private final Logger logger = LoggerFactory.getLogger(FriendsController.class);

    @Autowired
    public FriendsController(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    // TODO: Implement controller advice
    // TODO: Swagger documentation
    /**
     * Returns the friends of a specific user.
     *
     * @param userId User id of interest.
     * @return List as JSON string.
     */
    @GetMapping("/friends/of")
    public String getFriends(@RequestParam String userId) {
        logger.info("Requesting friends of {}", userId);
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Timmy");
        friends.add("Julia");
        friends.add("Peter");
        return jsonFactory.toJson(friends);
    }
}
