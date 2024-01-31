package com.boot.demo.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//identifies a class as a controller to handle incoming requests
public class HomeController {

    @GetMapping
    public String getSummat() {
        return "something";
    }

}