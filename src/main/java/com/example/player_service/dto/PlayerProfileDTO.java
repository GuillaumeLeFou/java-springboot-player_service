package com.example.player_service.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerProfileDTO {
    private Long id;
    private String name;
    private String pseudo;
    private int niveau;
    private int totalPoints;
    private List<FriendDTO> friends;
}
