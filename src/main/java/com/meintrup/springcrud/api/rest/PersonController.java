package com.meintrup.springcrud.api.rest;

import com.meintrup.springcrud.entities.Person;
import com.meintrup.springcrud.services.PersonService;
import com.meintrup.springcrud.util.JsonFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonService personService;
    private final JsonFactory jsonFactory;

    @Autowired
    public PersonController(PersonService personService, JsonFactory jsonFactory) {
        this.personService = personService;
        this.jsonFactory = jsonFactory;
    }

    @PostMapping("")
    @Operation(summary = "An endpoint that allows for person creation.", responses = {
            @ApiResponse(responseCode = "201", description = "Creates a Person", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{personId='a1fce6b5-0216-4bc0-83cc-86a8008b211c'}"))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    public ResponseEntity<String> postPerson(@RequestParam String name, @RequestParam String email) {
        Person person = Person.builder()
                              .name(name)
                              .build();
        personService.storePerson(person);
        HashMap<Object, Object> result = new HashMap<>();
        result.put("personId", person.getId()
                                 .toString());
        return new ResponseEntity<>(jsonFactory.toJson(result), HttpStatus.OK);
    }

    @GetMapping("")
    @Operation(summary = "Returns a list of all persons as json.", responses = {
            @ApiResponse(responseCode = "200", description = "Okay", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{persons=[" +
                            "    \"Person(id=fb7e6c50-1866-4d51-a166-970bb8d41ae3, name=TestPerson, email=test@mail.com)\",\n" +
                            "    \"Person(id=ee5ac4b1-3489-4dfe-ad61-ad7a0bbc990f, name=TestPerson1, email=test@mail.com)\",\n" +
                            "    \"Person(id=7e56307f-28bd-4790-afb1-23f9201553db, name=TestPerson2, email=test@mail.com)\",\n" +
                            "    \"Person(id=66fa0bf3-ad7a-4374-bc14-dd6ab8e63597, name=TestPerson3, email=test@mail.com)\"" +
                            "]}"))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    public ResponseEntity<String> getAllPersons() {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        result.put("persons", new ArrayList<>());
        Iterable<Person> it = personService.loadAllPersons();
        for (Person u: it) {
            result.get("persons").add(u.toString());
        }
        return new ResponseEntity<String>(jsonFactory.toJson(result), HttpStatus.OK);
    }
}
