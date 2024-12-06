package com.example.player_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private Long id;
    private String name;
    private String pseudo;
    private int niveau;
    private int totalPoints;
}
