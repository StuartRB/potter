package com.harry.potter.controller;

import com.harry.potter.user.NewUser;
import com.harry.potter.user.User;
import com.harry.potter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public Long addUser(@RequestBody NewUser newUser){
        return userService.addUser(newUser);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
