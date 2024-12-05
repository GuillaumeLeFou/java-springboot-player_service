package com.example.player_service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.player_service.entity.Player;
import com.example.player_service.repository.PlayerRepository;

@Component
public class PlayerDAO implements IPlayerDAO {

    @Autowired
    private PlayerRepository playerRepository;
    
    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
    
    // @Override
    // public Player findByPseudo(String pseudo) {
    //     return playerRepository.findByPseudo(pseudo);
    // }

    @Override
    public boolean emailExists(String email) {
        return playerRepository.existsByEmail(email);
    }

    @Override
    public boolean pseudoExists(String pseudo) {
        return playerRepository.existsByPseudo(pseudo);
    }

    
}
