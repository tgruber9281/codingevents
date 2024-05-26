package com.launchcode.codingevents.controllers;

import com.launchcode.codingevents.data.EventRepository;
import com.launchcode.codingevents.models.Event;
import com.launchcode.codingevents.models.EventType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Trevor Gruber
 */
@Controller
@RequestMapping("events")
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;
    
    //findAll, save, findById
    
    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title","Coding Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }
    
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors,
                                         Model model){
        if (errors.hasErrors()){
            model.addAttribute("title","Create Event");
            model.addAttribute(new Event());
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:/events";
    }
    
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title","Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }
    
    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Optional<Event> result = eventRepository.findById(eventId);
        if (result.isEmpty()){
        
        } else {
            Event eventToEdit = result.get();
            String title =
                    "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() +
                            ")";
            model.addAttribute("event", eventToEdit);
            model.addAttribute("title", title );
        }
        
        model.addAttribute("types", EventType.values());
        return "events/edit";
    }
    //TODO figure out how to make the edit event not increment the id (add id setter?)
    @PostMapping("edit")
    public String processEditForm(@ModelAttribute @Valid Event event, Errors errors,
                                  Model model, int id) {
        if (errors.hasErrors()){
            String title = "Edit Event " + event.getName() + " (id=" + event.getId() + ")";
            model.addAttribute("title", title );
            return "events/edit";
        }
        Optional<Event> result = eventRepository.findById(id);
        if (result.isEmpty()){
        
        } else {
            Event eventToEdit = result.get();
            eventToEdit.setName(event.getName());
            eventToEdit.setDescription(event.getDescription());
            eventToEdit.setContactEmail(event.getContactEmail());
            eventToEdit.setType(event.getType());
            eventRepository.save(eventToEdit);
        }
        return "redirect:/events";
    }
}
