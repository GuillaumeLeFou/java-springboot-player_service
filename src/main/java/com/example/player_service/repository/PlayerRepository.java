package com.example.player_service.repository;

import java.util.Optional;

import com.example.player_service.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByPseudo(String pseudo);
    boolean existsByPseudo(String pseudo);
    boolean existsByEmail(String email);
}