package com.artem.usenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"login", "registration", "user","/"})
public class FrontendController {
    @GetMapping
    public String getPage() {
        return "forward:/index.html";
    }
}
