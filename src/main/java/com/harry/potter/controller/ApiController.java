package com.harry.potter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class ApiController {

    @GetMapping("public/test1")
    public String test1(){
        return "Public API Test 1";
    }

    @GetMapping("public/test2")
    public String test2(){
        return "Public API Test 2";
    }

    @GetMapping("private/test3")
    public String test3(){
        return "Private API Test 3";
    }


}