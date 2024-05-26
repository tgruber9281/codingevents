package com.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Created by Trevor Gruber
 */
@Entity
public class EventCategory {

/** fields */
    @Id
    @GeneratedValue
    private int id;
    
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;
    
/** Constructor(s) */
    public EventCategory(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        this.name = name;
    }
    
    public EventCategory() {}
    
/** Custom methods */

/** Getters and Setters */
    public String getName() {
        return name;
    }
    
    public void setName(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
/** toString */
    @Override
    public String toString() {
        return name;
    }

/** Equals and Hashcode*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventCategory that = (EventCategory) o;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
