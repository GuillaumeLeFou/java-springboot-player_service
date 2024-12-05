package com.example.player_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.player_service.dao.IPlayerDAO;
import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.entity.Player;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private IPlayerDAO playerDAO;

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

    @Override
    public Player registerPlayer(PlayerCreateDTO playerCreateDTO) {
        Player player = converteToPlayerEntity(playerCreateDTO);
        return playerDAO.save(player);
        
    }


    
}
