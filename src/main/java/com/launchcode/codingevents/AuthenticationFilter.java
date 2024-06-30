package com.launchcode.codingevents;

import com.launchcode.codingevents.controllers.AuthenticationController;
import com.launchcode.codingevents.data.UserRepository;
import com.launchcode.codingevents.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Trevor Gruber
 */
public class AuthenticationFilter implements HandlerInterceptor {
    
    /* fields */
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AuthenticationController authenticationController;
    
    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");
    
    /* Custom methods */
    
    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {
        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }
        
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        
        //The user is logged in
        if (user != null){
            authenticationController.setLoggedInUser(user);
            return true;
        }
        
        //The user is not logged in
        authenticationController.setLoggedInUser(null);
        response.sendRedirect("/login");
        return false;
    }
    
    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                    @Nullable ModelAndView modelAndView) throws IOException {
        
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        
        //The user is logged in
        if (user != null){
            authenticationController.setLoggedInUser(user);
        } else {
            //The user is not logged in
            authenticationController.setLoggedInUser(null);
        }
    }*/
    
    /* Getters and Setters */
    
    /* toString */
    
    /* Equals and Hashcode*/
    
}
