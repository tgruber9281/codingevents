package com.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Created by Trevor Gruber
 */
@Entity
public class EventDetails extends AbstractEntity{
    
    /** fields */
    
    @Size(max = 500, message = "Description cannot be more than 500 characters")
    private String description;
    
    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Email is required")
    private String contactEmail;
    
    @OneToOne (mappedBy = "eventDetails")
    private Event event;
    
    /** Constructor(s) */
    public EventDetails(String description, String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
    }
    
    public EventDetails() {
    }
    /** Custom methods */
    
    /** Getters and Setters */
    public @Size(max = 500, message = "Description cannot be more than 500 characters") String getDescription() {
        return description;
    }
    
    public void setDescription(@Size(max = 500, message = "Description cannot be more than 500 characters") String description) {
        this.description = description;
    }
    
    public @Email(message = "Please enter a valid email address") @NotBlank(message = "Email is required") String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(@Email(message = "Please enter a valid email address") @NotBlank(message = "Email is required") String contactEmail) {
        this.contactEmail = contactEmail;
    }
    /** toString */
    
    /** Equals and Hashcode*/
    
}
