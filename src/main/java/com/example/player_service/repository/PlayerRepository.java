package com.example.game_service.repository;

import java.util.Optional;

import com.example.game_service.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByPseudo(String pseudo);
    boolean existsByPseudo(String pseudo);
    boolean existsByEmail(String email);
}