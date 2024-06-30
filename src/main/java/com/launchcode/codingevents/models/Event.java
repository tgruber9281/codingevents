package com.launchcode.codingevents.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor Gruber
 */
@Entity
public class Event extends AbstractEntity {
    
    /** fields */
    
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
    
    @ManyToMany
    private final List<User> users = new ArrayList<>();

//    @NotBlank
//    private String location;
    
    /** Constructor(s) */
    
    public Event(String name, String description, String contactEmail, EventCategory eventCategory,
                 String location) {
        this.name = name;
        this.eventCategory = eventCategory;
//        this.location = location;
    }
    
    public Event() {
    }
    
    /** Custom methods */
    
    /** Getters and Setters */
    
    public List<User> getUsers() {
        return users;
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
    
    /** toString */
    
    @Override
    public String toString() {
        return name;
    }
    
    /** Equals and Hashcode*/
}