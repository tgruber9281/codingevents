package com.launchcode.codingevents.models.dto;

import com.launchcode.codingevents.models.Event;
import com.launchcode.codingevents.models.User;
import jakarta.validation.constraints.NotNull;

/**
 * Created by Trevor Gruber
 */

public class UserEventDTO {
    
    /** fields */
    @NotNull
    private Event event;
    
    @NotNull
    private User user;
    
    /** Constructor(s) */
    public UserEventDTO() {
    }
    /** Custom methods */
    
    /** Getters and Setters */
    public @NotNull Event getEvent() {
        return event;
    }
    
    public void setEvent(@NotNull Event event) {
        this.event = event;
    }
    
    public @NotNull User getUser() {
        return user;
    }
    
    public void setUser(@NotNull User user) {
        this.user = user;
    }
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}
