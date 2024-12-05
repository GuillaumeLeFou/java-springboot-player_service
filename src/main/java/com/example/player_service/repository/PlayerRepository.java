package com.example.player_service.repository;

import com.example.player_service.entity.Player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Player findByPseudo(String pseudo);
    boolean existsByPseudo(String pseudo);
    boolean existsByEmail(String email);
    Player findByPseudo(String pseudo);
    Optional<Player> findById(Long id);
}