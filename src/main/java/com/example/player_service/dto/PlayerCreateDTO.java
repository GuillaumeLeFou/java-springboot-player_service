package com.example.player_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerCreateDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Pseudo is required")
    private String pseudo;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
}
