package com.example.player_service.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.player_service.dto.PlayerStatsDTO;
import com.example.player_service.entity.Friend;
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

    @Override
    public boolean emailExists(String email) {
        return playerRepository.existsByEmail(email);
    }

    @Override
    public boolean pseudoExists(String pseudo) {
        return playerRepository.existsByPseudo(pseudo);
    }

    @Override
    public Player findByPseudo(String pseudo) {
        return playerRepository.findByPseudo(pseudo);
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return playerRepository.existsById(id);
    }

    @Override
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    
}
