package com.liam.softwaredesign.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class health {

    @GetMapping("/healthCheck")
    public String index(){

        return "Microservice Is Up And Running";
    }

}
