package com.example.player_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerUpdateRequestDTO {
    private Long playerId;
    private int score;
    private boolean victoire;
}
