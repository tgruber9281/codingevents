package com.launchcode.codingevents.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

/**
 * Created by Trevor Gruber
 */
@MappedSuperclass
public abstract class AbstractEntity {
    
    /** fields */
    @Id
    @GeneratedValue
    private int id;
    
    /** Constructor(s) */
    public AbstractEntity() {
    }
    
    /** Custom methods */
    
    /** Getters and Setters */
    public int getId() {
        return id;
    }
    
    /** toString */
    
    /** Equals and Hashcode*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
}
