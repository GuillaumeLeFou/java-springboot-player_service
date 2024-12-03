package com.example.player_service.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.player_service.entity.Player;
import com.example.player_service.repository.PlayerRepository;

public class PlayerDAO implements IPlayerDAO {

    @Autowired
    private PlayerRepository playerRepository;
    
    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
    
    @Override
    public Player findByPseudo(String pseudo) {
        return playerRepository.findByPseudo(pseudo);
    }

    
}
