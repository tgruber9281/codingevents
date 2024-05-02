package com.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by Trevor Gruber
 */
@Controller
@RequestMapping("events")
public class EventController {
    @GetMapping("")
    public String displayAllEvents(Model model) {
        ArrayList<String> events = new ArrayList<>();
        events.add("Sonshine");
        events.add("Avengers Movie");
        events.add("Easter Eggstravaganza");
        events.add("Christmas");
        model.addAttribute("events", events);
        return "events/index";
    }
    
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }
}
