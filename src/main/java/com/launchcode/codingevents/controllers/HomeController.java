package com.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Trevor Gruber
 */
@Controller
public class HomeController {
    @GetMapping
    public String HomeController(){
        return "index";
    }
}