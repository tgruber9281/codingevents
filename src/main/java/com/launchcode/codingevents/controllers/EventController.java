package com.launchcode.codingevents.controllers;

import com.launchcode.codingevents.data.EventCategoryRepository;
import com.launchcode.codingevents.data.EventRepository;
import com.launchcode.codingevents.models.Event;
import com.launchcode.codingevents.models.EventCategory;
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
    
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    
    //findAll, save, findById
    
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId,
                                   Model model) {
        if (categoryId == null) {
            model.addAttribute("title", "Coding Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()){
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in Category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
    }
    
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors,
                                         Model model){
        if (errors.hasErrors()){
            model.addAttribute("title","Create Event");
            model.addAttribute("categories", eventCategoryRepository.findAll());
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
        
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/edit";
    }
    //TODO figure out how to make the edit event not increment the id (add id setter?)
    @PostMapping("edit")
    public String processEditForm(@ModelAttribute @Valid Event event, Errors errors,
                                  Model model, int id) {
        if (errors.hasErrors()){
            String title = "Edit Event " + event.getName() + " (id=" + event.getId() + ")";
            model.addAttribute("title", title );
            model.addAttribute("categories", eventCategoryRepository.findAll());
            return "events/edit";
        }
        Optional<Event> result = eventRepository.findById(id);
        if (result.isEmpty()){
        
        } else {
            Event eventToEdit = result.get();
            eventToEdit.setName(event.getName());
            eventToEdit.setEventDetails(event.getEventDetails());
            eventToEdit.setEventCategory(event.getEventCategory());
            eventRepository.save(eventToEdit);
        }
        return "redirect:/events";
    }
}
