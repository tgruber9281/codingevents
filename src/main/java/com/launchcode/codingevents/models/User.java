package com.launchcode.codingevents.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Trevor Gruber
 */
@Entity
public class User extends AbstractEntity {
    
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    /** fields */
    
    @NotNull
    private String username;
    
    @NotNull
    private String pwdHash;
    
    @NotNull
    @Valid
    @OneToOne (cascade = CascadeType.ALL)
    private UserDetails userDetails;
    
    public User(String username, String pwdHash, UserDetails userDetails) {
        this.username = username;
        this.pwdHash = encoder.encode(pwdHash);
        this.userDetails = userDetails;
    }
    
    /** Constructor(s) */
    
    
    public User() {
    }
    /** Custom methods */
    
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwdHash);
    }
    
    /** Getters and Setters */
    
    public @NotNull @Valid UserDetails getUserDetails() {
        return userDetails;
    }
    
    public void setUserDetails(@NotNull @Valid UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    
    public @NotNull String getUsername() {
        return username;
    }
    
    /** toString */
    
    /** Equals and Hashcode*/
    
}