package com.artem.usenko.controller;

import com.artem.usenko.dto.User;
import com.artem.usenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("{login}")
    public ResponseEntity<User> isConfirm(@PathVariable("login") String login) throws IOException {
        User userByLogin = userService.getUserByLogin(login);
        return new ResponseEntity<>(userByLogin, HttpStatus.OK);
    }


}
