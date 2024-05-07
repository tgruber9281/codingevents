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
        model.addAttribute("title","Coding Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }
    
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        return "events/create";
    }
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent){
        EventData.add(newEvent);
        return "redirect:/events";
    }
    
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",EventData.getAll());
        return "events/delete";
    }
    
    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:/events";
    }
}
