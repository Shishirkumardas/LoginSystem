package com.example.loginsystem.Models;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    public JwtResponse(String token) { this.token = token; }
    // getters and setters
}
