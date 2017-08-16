package com.artem.usenko.controller;


import com.artem.usenko.dto.User;
import com.artem.usenko.service.RegService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("api/registry")
public class RegistrationController {

    private final RegService regService;

    @Autowired
    public RegistrationController(RegService regService) {
        this.regService = regService;
    }

    @PostMapping
    public ResponseEntity<User> registration(@Valid @RequestBody User user, BindingResult bindingResult) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
        User registration = regService.registration(user);
        if (registration == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(registration, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Boolean> isRegUser(@RequestParam("login") String login) throws IOException {
        Boolean aBoolean = regService.thisLoginAlreadyNotExists(login);
        return new ResponseEntity<>(aBoolean, HttpStatus.OK);
    }

}
