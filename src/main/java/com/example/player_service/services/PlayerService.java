package com.example.player_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.player_service.dao.PlayerDAO;
import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.entity.Player;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    @Override
    public Player createPlayer(PlayerCreateDTO playerCreateDTO) {
        Player savedPlayer = playerDAO.save(converteToPlayerEntity(playerCreateDTO));
        return converteToCreatePlayerDTO(savedPlayer);
    }

    @Override
    public Player findByPseudo(String pseudo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPseudo'");
    }

    @Override
    public PlayerCreateDTO converteToCreatePlayerDTO(Player player) {
        PlayerCreateDTO playerCreateDTO = new PlayerCreateDTO();
        playerCreateDTO.setName(player.getName());
        playerCreateDTO.setPseudo(player.getPseudo());
        playerCreateDTO.setEmail(player.getEmail());
        return playerCreateDTO;
    }

    @Override
    public Player converteToPlayerEntity(PlayerCreateDTO playerCreateDTO) {
        Player player = new Player();
        player.setName(playerCreateDTO.getName());
        player.setPseudo(playerCreateDTO.getPseudo());
        player.setEmail(playerCreateDTO.getEmail());
        return player;
    }


    
}
