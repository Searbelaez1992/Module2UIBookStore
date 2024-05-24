package com.example.BookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {
    @GetMapping("/usersN")
    public String usersN(){
        return "usersN";
    }
}
