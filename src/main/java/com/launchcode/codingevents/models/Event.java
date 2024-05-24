package com.launchcode.codingevents.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Created by Trevor Gruber
 */
@Entity
public class Event {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    
    @Size(max = 500, message = "Description cannot be more than 500 characters")
    private String description;
    
    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Email is required")
    private String contactEmail;
    
//    @NotNull
    private EventType type;
    
//    @NotBlank
//    private String location;
    
    public Event(String name, String description, String contactEmail, EventType type, String location) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.type = type;
//        this.location = location;
    }
    
    public Event() {    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
    public int getId() {
        return id;
    }
   
    public @NotNull EventType getType() {
        return type;
    }
    
    public void setType(@NotNull EventType type) {
        this.type = type;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
