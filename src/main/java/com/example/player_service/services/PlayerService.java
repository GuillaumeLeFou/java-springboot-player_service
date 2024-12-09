package com.example.player_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.player_service.dao.IFriendDAO;
import com.example.player_service.dao.IPlayerDAO;
import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.dto.PlayerStatsDTO;
import com.example.player_service.dto.PlayerUpdateDTO;
import com.example.player_service.entity.Player;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private IPlayerDAO playerDAO;

    @Autowired
    private IFriendDAO friendDAO;

    @Override
    public Player getPlayerByPseudo(String pseudo) {
        return playerDAO.findByPseudo(pseudo);
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

    @Override
    public Player getPlayerById(Long id) {
        return playerDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Player not found"));
    }

    @Override
    public Player updatePlayer(Long id, PlayerUpdateDTO playerUpdateDTO) {
        Player player = playerDAO.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Player not found with id"));
        
        if(playerDAO.emailExists(playerUpdateDTO.getEmail()) && !player.getEmail().equals(playerUpdateDTO.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        if(playerDAO.pseudoExists(playerUpdateDTO.getPseudo()) && !player.getPseudo().equals(playerUpdateDTO.getPseudo())){
            throw new IllegalArgumentException("Pseudo already exists");
        }

        player.setEmail(playerUpdateDTO.getEmail());
        player.setName(playerUpdateDTO.getName());
        player.setPseudo(playerUpdateDTO.getPseudo());

        return playerDAO.save(player);
    }

    @Override
    public void deletePlayerById(Long id) {
        if(!playerDAO.existsById(id)){
            throw new IllegalArgumentException("Player not found by id");
        }
        friendDAO.deleteFriendship(id);
        playerDAO.deletePlayerById(id);
    }

    @Override
    public PlayerStatsDTO getStatsPlayer(Long id) {
        PlayerStatsDTO player = convertToPlayerStats(playerDAO.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Player not found with id")));
        return player;
    }

    @Override
    public PlayerStatsDTO convertToPlayerStats(Player player) {
        PlayerStatsDTO playerStats = new PlayerStatsDTO();
        playerStats.setNiveau(player.getNiveau());
        playerStats.setTotalPoints(player.getTotalPoints());
        return playerStats;
    }
    
}
