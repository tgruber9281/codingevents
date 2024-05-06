package com.launchcode.codingevents.controllers;

import com.launchcode.codingevents.data.EventData;
import com.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Trevor Gruber
 */
@Controller
@RequestMapping("events")
public class EventController {
    
    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }
    
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, @RequestParam String eventDesc){
        EventData.add(new Event(eventName, eventDesc));
        return "redirect:/events";
    }
}
