package com.launchcode.codingevents.controllers;

import com.launchcode.codingevents.data.UserDetailsRepository;
import com.launchcode.codingevents.data.UserRepository;
import com.launchcode.codingevents.models.User;
import com.launchcode.codingevents.models.UserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("user")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserDetailsRepository userDetailsRepository;
    
    @Autowired
    AuthenticationController authenticationController;
    
    @GetMapping("profile")
    public String renderUserProfile(Model model, @RequestParam int id){
        model.addAttribute("loggedInUser", authenticationController.getLoggedInUser().getId());
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            User user = result.get();
            model.addAttribute("user", user);
            model.addAttribute("title", "User Profile");
            return "user/profile";
        } else {
            return "redirect:/register";
        }
    }
    
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("loggedInUser", authenticationController.getLoggedInUser().getId());
        model.addAttribute("title","Create User");
        model.addAttribute("user", new User());
        return "user/create";
    }
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid User newUser, Errors errors,
                                         Model model){
        model.addAttribute("loggedInUser", authenticationController.getLoggedInUser().getId());
        if (errors.hasErrors()){
            model.addAttribute("title","Create User");
            model.addAttribute("categories", userRepository.findAll());
            return "user/create";
        }
        userRepository.save(newUser);
        return "redirect:/user/profile?id=" + authenticationController.getLoggedInUser().getId();
    }
    @GetMapping("edit")
    public String displayEditForm(Model model, @RequestParam int id) {
        model.addAttribute("loggedInUser", authenticationController.getLoggedInUser().getId());
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()){
            User userToEdit = result.get();
            String title =
                    "Edit User " + userToEdit.getUserDetails().getFirstName() + " " + userToEdit.getUserDetails().getLastName();
            model.addAttribute("userDetails", userToEdit.getUserDetails());
            model.addAttribute("title", title );
        }
        
        return "user/edit";
    }
    @PostMapping("edit")
    public String processEditForm(@ModelAttribute @Valid UserDetails userDetails, Errors errors,
                                  Model model, int id) {
        model.addAttribute("loggedInUser", authenticationController.getLoggedInUser().getId());
        
        if (errors.hasErrors()){
            String title =
                    "Edit User " + userDetails.getFirstName() + " " + userDetails.getLastName();
            model.addAttribute("title", title );
            return "user/edit";
        }
        UserDetails userToEdit = userDetailsRepository.getReferenceById(userDetails.getId());
        userToEdit = userDetails;
        userDetailsRepository.save(userToEdit);
        return "redirect:/user/profile?id=" + id;
    }
}
