package com.example.player_service.services;

import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.dto.PlayerUpdateDTO;
import com.example.player_service.entity.Player;

public interface IPlayerService {
    Player registerPlayer(PlayerCreateDTO playerCreateDTO);
    Player getPlayerByPseudo(String pseudo);
    Player getPlayerById(Long id);
    PlayerCreateDTO converteToCreatePlayerDTO(Player player);
    Player converteToPlayerEntity(PlayerCreateDTO playerCreateDTO);
    Player updatePlayer(Long id, PlayerUpdateDTO playerUpdateDTO);
    void deletePlayerById(Long id);
    // void addFriend(Long id, Long idFriend);
} 