package com.launchcode.codingevents.controllers;

import com.launchcode.codingevents.data.UserRepository;
import com.launchcode.codingevents.models.User;
import com.launchcode.codingevents.models.dto.LoginFormDTO;
import com.launchcode.codingevents.models.dto.RegistrationFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Created by Trevor Gruber
 */

@Controller
public class AuthenticationController {
    
    @Autowired
    UserRepository userRepository;
    
    private static User loggedInUser;
    private static final String userSessionKey = "user";
    
    public User getUserFromSession(HttpSession session){
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            return null;
        }
        return user.get();
    }
    
    public static void setUserInSession(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }
    
    @GetMapping("/register")
    public String displayRegistrationForm(Model model){
        model.addAttribute(new RegistrationFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }
    
    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO, Errors errors,
                                          HttpServletRequest request, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }
        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());
        
        if (existingUser != null){
            errors.rejectValue("username", "username.alreadyexists", "A user with that username " +
                    "already exists");
            model.addAttribute("title", "Register");
            return "register";
        }
        
        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)){
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }
        
        User newUser = new User(registrationFormDTO.getUsername(),
                registrationFormDTO.getPassword(),registrationFormDTO.getUserDetails());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        
        return "redirect:";
    }
    
    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }
    
    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   HttpServletRequest request, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }
        
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (theUser == null){
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "/login";
        }
        String password = loginFormDTO.getPassword();
        if (!theUser.isMatchingPassword(password)){
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "/login";
        }
        
        setUserInSession(request.getSession(), theUser);
        loggedInUser = theUser;
        return "redirect:";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }
    
    public User getLoggedInUser() {
        return loggedInUser;
    }
    
    public void setLoggedInUser(User loggedInUser) {
        AuthenticationController.loggedInUser = loggedInUser;
    }
}