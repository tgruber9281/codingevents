package com.launchcode.codingevents.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Created by Trevor Gruber
 */

public class LoginFormDTO {
    
    /* fields */
    @NotNull
    @NotBlank
    @Size (min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.")
    private String username;
    
    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;
    
    /* Constructor(s) */
    
    /* Custom methods */
    
    /* Getters and Setters */
    
    public @NotNull @NotBlank @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.") String getUsername() {
        return username;
    }
    
    public void setUsername(@NotNull @NotBlank @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.") String username) {
        this.username = username;
    }
    
    public @NotNull @NotBlank @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.") String getPassword() {
        return password;
    }
    
    public void setPassword(@NotNull @NotBlank @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.") String password) {
        this.password = password;
    }
    
    /* toString */
    
    /* Equals and Hashcode*/
    
}