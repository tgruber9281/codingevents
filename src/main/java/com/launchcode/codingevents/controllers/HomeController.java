package com.launchcode.codingevents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Trevor Gruber
 */
@Controller
public class HomeController {
    
    @Autowired
    AuthenticationController authenticationController;
    
    @GetMapping
    public String HomeController(Model model){
        model.addAttribute("loggedInUser", authenticationController.getLoggedInUser().getId());
        return "index";
    }
}