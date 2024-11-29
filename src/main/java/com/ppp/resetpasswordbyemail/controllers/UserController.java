package com.ppp.resetpasswordbyemail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ppp.resetpasswordbyemail.models.User;
import com.ppp.resetpasswordbyemail.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestParam String email, @RequestParam String password) {
        return userService.createUser(email, password);
    }
}
