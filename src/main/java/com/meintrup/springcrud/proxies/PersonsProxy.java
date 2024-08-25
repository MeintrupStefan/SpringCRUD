package com.meintrup.springcrud.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "persons",
        url = "${proxy.test.self}"
)
public interface PersonsProxy {
    @GetMapping("/api/v1/persons")
    ResponseEntity<String> getProxiedPersons(
            @RequestHeader String authorization
    );
}
