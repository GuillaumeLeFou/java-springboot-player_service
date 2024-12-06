package com.example.player_service.dto;


import com.example.player_service.entity.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendDTO {
    private Long id;
    private Player player;
    private Player friend;
}
