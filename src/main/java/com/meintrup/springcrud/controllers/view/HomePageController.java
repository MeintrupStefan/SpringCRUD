package com.meintrup.springcrud.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    @RequestMapping("/")
    public String getHomePage() {
        return "crudPerson.html";
    }
}
