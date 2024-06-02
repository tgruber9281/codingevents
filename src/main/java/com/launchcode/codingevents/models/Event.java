package com.launchcode.codingevents.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Created by Trevor Gruber
 */
@Entity
public class Event extends AbstractEntity {
    
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;
    
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

//    @NotBlank
//    private String location;
    
    public Event(String name, String description, String contactEmail, EventCategory eventCategory,
                 String location) {
        this.name = name;
        this.eventCategory = eventCategory;
//        this.location = location;
    }
    
    public Event() {
    }
    
    public @NotBlank(message = "Name is required") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String getName() {
        return name;
    }
    
    public void setName(@NotBlank(message = "Name is required") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name) {
        this.name = name;
    }
    
    public @NotNull(message = "Category is required") EventCategory getEventCategory() {
        return eventCategory;
    }
    
    public void setEventCategory(@NotNull(message = "Category is required") EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
    
    public EventDetails getEventDetails() {
        return eventDetails;
    }
    
    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
    
    //    public @NotBlank String getLocation() {
//        return location;
//    }

//    public void setLocation(@NotBlank String location) {
//        this.location = location;
//    }
    
    @Override
    public String toString() {
        return name;
    }
}