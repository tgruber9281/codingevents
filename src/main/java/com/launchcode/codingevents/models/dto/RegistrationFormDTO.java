package com.launchcode.codingevents.models.dto;

import com.launchcode.codingevents.models.UserDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Created by Trevor Gruber
 */

public class RegistrationFormDTO extends LoginFormDTO{
    
    /* fields */
    
    private String verifyPassword;
    
    @NotNull
    @Valid
    private UserDetails userDetails;
    
    /* Constructor(s) */
    
    /* Custom methods */
    
    /* Getters and Setters */
    
    public String getVerifyPassword() {
        return verifyPassword;
    }
    
    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
    
    public @NotNull @Valid UserDetails getUserDetails() {
        return userDetails;
    }
    
    public void setUserDetails(@NotNull @Valid UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    
    /* toString */
    
    /* Equals and Hashcode*/
    
}