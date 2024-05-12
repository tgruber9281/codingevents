package com.launchcode.codingevents.controllers;

import com.launchcode.codingevents.data.EventData;
import com.launchcode.codingevents.models.Event;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
        model.addAttribute("event", new Event());
        return "events/create";
    }
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors error,
                                         Model model){
        if (error.hasErrors()){
            model.addAttribute("title","Create Event");
            return "events/create";
        }
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
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title );
        return "events/edit";
    }
    //TODO figure out how to make the edit event not increment the id
    @PostMapping("edit")
    public String processEditForm(@ModelAttribute @Valid Event event, Errors errors,
                                  Model model) {
        if (errors.hasErrors()){
            String title = "Edit Event " + event.getName() + " (id=" + event.getId() + ")";
            model.addAttribute("title", title );
            return "events/edit";
        }
        Event eventToEdit = EventData.getById(event.getId());
        eventToEdit.setName(event.getName());
        eventToEdit.setDescription(event.getDescription());
        eventToEdit.setContactEmail(event.getContactEmail());
        return "redirect:/events";
    }
}
