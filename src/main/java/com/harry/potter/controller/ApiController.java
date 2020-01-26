package com.harry.potter.controller;


import com.harry.potter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")

public class ApiController {

    @Autowired
    UserService userService;

    @GetMapping("public/test1")
    public String test1() {
        return "Public API Test 1";
    }

    @GetMapping("public/test2")
    public String test2() {
        return "Public API Test 2";
    }

    @GetMapping("private/test3")
    public String test3() {
        return "Private API Test 3";
    }

//    @GetMapping("public/user")
//    public List<User> getAllUsers(){
//        return userService.getAllUsers();
//    }


}