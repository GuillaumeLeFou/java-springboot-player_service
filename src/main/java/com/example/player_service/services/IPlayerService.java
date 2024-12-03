package com.example.player_service.services;

import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.entity.Player;

public interface IPlayerService {
    Player createPlayer(PlayerCreateDTO playerCreateDTO);
    Player findByPseudo(String pseudo);
    PlayerCreateDTO converteToCreatePlayerDTO(Player player);
    Player converteToPlayerEntity(PlayerCreateDTO playerCreateDTO);
} 