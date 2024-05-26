package com.launchcode.codingevents.controllers;

import com.launchcode.codingevents.data.EventCategoryRepository;
import com.launchcode.codingevents.models.EventCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Trevor Gruber
 */
@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

/** fields */
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping("")
    public String displayAllCategories(Model model){
        model.addAttribute("title","All Categories");
        model.addAttribute("categories",eventCategoryRepository.findAll());
        return "eventCategories/index";
    }
    
    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute("category", new EventCategory());
        return "eventCategories/create";
    }
    
    @PostMapping("create")
    public String processCreateEventCategoryForm(Model model,
                                                 @ModelAttribute @Valid EventCategory newEventCategory,
                                                 Errors errors){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute("category", new EventCategory());
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:/eventCategories";
    }
}
