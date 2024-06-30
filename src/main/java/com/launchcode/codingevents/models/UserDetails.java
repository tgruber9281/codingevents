package com.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor Gruber
 */
@Entity
public class UserDetails extends AbstractEntity {
    
    /** fields */
    
    @Size(max = 50, message = "Display name cannot be more than 50 characters")
    private String displayName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String emailAddress;
    
    @NotBlank (message = "First name is required")
    @Size(min = 1, max = 50, message = "First name cannot be more than 50 characters.")
    private String firstName;
    
    @NotBlank (message = "last name is required")
    @Size(min = 1, max = 50, message = "Last name cannot be more than 50 characters.")
    private String lastName;
    
    @ManyToMany
    private final List<Event> events = new ArrayList<>();
    
    @OneToOne (mappedBy = "userDetails")
    private User user;
    
    /** Constructor(s) */
    public UserDetails(String displayName, String emailAddress, String firstName, String lastName, User user) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }
    
    public UserDetails(String displayName, String emailAddress, String firstName, String lastName) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public UserDetails() {
    }
    
    /** Custom methods */
    
    public void addEvent(Event event) {
        events.add(event);
    }
    
    public String getDisplayName(){
        return displayName;
    }
    
    /** Getters and Setters */
    
    public List<Event> getEvents() {
        return events;
    }
    
    public void setDisplayName(@Size(max = 50, message = "Display name cannot be more than 50 characters") String displayName) {
        this.displayName = displayName;
    }
    
    public @NotBlank(message = "Email is required") @Email(message = "Enter a valid email address") String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(@NotBlank(message = "Email is required") @Email(message = "Enter a valid email address") String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public @NotBlank(message = "First name is required") @Size(min = 1, max = 50, message = "First name cannot be more than 50 characters.") String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(@NotBlank(message = "First name is required") @Size(min = 1, max = 50, message = "First name cannot be more than 50 characters.") String firstName) {
        this.firstName = firstName;
    }
    
    public @NotBlank(message = "last name is required") @Size(min = 1, max = 50, message = "Last name cannot be more than 50 characters.") String getLastName() {
        return lastName;
    }
    
    public void setLastName(@NotBlank(message = "last name is required") @Size(min = 1, max = 50, message = "Last name cannot be more than 50 characters.") String lastName) {
        this.lastName = lastName;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}