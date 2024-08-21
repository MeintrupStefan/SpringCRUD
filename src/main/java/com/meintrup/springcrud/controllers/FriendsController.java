package com.meintrup.springcrud.controllers;

import com.meintrup.springcrud.util.GsonJsonFactory;
import com.meintrup.springcrud.util.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FriendsController {
    private final JsonFactory jsonFactory;

    @Autowired
    public FriendsController(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    // TODO: Swagger documentation
    @GetMapping("/friends")
    public String getFriends() {
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Timmy");
        friends.add("Julia");
        friends.add("Peter");
        return jsonFactory.toJson(friends);
    }
}
