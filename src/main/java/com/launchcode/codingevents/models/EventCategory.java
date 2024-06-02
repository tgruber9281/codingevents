package com.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor Gruber
 */
@Entity
public class EventCategory extends AbstractEntity {

    /** fields */

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    
    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();
    
    /** Constructor(s) */
    public EventCategory(String name) {
        this.name = name;
    }
    
    public EventCategory() {}
    
    /** Custom methods */
    

    /** Getters and Setters */
    public @NotBlank(message = "Name is required") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String getName() {
        return name;
    }
    
    public void setName(@NotBlank(message = "Name is required") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name) {
        this.name = name;
    }
    
    public List<Event> getEvents() {
        return events;
    }
    
    /** toString */
    @Override
    public String toString() {
        return name;
    }
}
