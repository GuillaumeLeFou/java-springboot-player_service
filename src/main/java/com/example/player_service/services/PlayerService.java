package com.example.player_service.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.player_service.dto.FriendDTO;
import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.dto.PlayerProfileDTO;
import com.example.player_service.entity.Player;
import com.example.player_service.repository.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
public class PlayerService {;
    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    public PlayerProfileDTO createPlayer(PlayerCreateDTO playerDTO) {
        // Validate unique constraints
        if (playerRepository.existsByPseudo(playerDTO.getPseudo())) {
            throw new RuntimeException("Pseudo already exists");
        }
        if (playerRepository.existsByEmail(playerDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new player
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setPseudo(playerDTO.getPseudo());
        player.setEmail(playerDTO.getEmail());
        player.setNiveau(1); // Default starting level
        player.setTotalPoints(0); // Default starting points

        Player savedPlayer = playerRepository.save(player);
        return convertToProfileDTO(savedPlayer);
    }

    private PlayerProfileDTO convertToProfileDTO(Player player) {
        PlayerProfileDTO profileDTO = new PlayerProfileDTO();
        profileDTO.setId(player.getId());
        profileDTO.setName(player.getName());
        profileDTO.setPseudo(player.getPseudo());
        profileDTO.setNiveau(player.getNiveau());
        profileDTO.setTotalPoints(player.getTotalPoints());
        return profileDTO;
    }
}
