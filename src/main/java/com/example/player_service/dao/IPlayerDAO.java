package com.example.player_service.dao;

import com.example.player_service.entity.Player;

public interface IPlayerDAO {
    Player save(Player player);
    Player findByPseudo(String pseudo);
}
