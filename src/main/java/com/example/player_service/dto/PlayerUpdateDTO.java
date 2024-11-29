package com.example.player_service.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerUpdateDTO {
    private String name;
    private String pseudo;
    
    @Email(message = "Invalid email address")
    private String email;
}
