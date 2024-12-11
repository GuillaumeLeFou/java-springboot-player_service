package com.example.player_service.dao;

import java.util.Optional;

import com.example.player_service.entity.Player;

public interface IPlayerDAO {
    Player save(Player player);
    boolean emailExists(String email);
    boolean pseudoExists(String pseudo);
    boolean existsById(Long id);
    void deletePlayerById(Long id);
    Player findByPseudo(String pseudo);
    Optional<Player> findById(Long id);

}
