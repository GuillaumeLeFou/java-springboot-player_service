package com.example.player_service.controller;

import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.dto.PlayerProfileDTO;
import com.example.player_service.dto.PlayerUpdateDTO;
import com.example.player_service.entity.Player;
import com.example.player_service.services.IPlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @PostMapping("/joueurs")
    public ResponseEntity<Player> registerPlayer(
        @Valid @RequestBody PlayerCreateDTO playerDTO) {
            Player player = playerService.registerPlayer(playerDTO);
            return ResponseEntity.ok(player);
    }

    @GetMapping("/pseudo/{pseudo}")
    public ResponseEntity<Player> getPlayerByPseudo(@PathVariable String pseudo){
        try {
            Player player = playerService.getPlayerByPseudo(pseudo);
            return ResponseEntity.ok(player);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/joueurs/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        try {
            Player player = playerService.getPlayerById(id);
            return ResponseEntity.ok(player);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerUpdateDTO playerUpdateDTO){
        try{
            Player updatePlayer = playerService.updatePlayer(id, playerUpdateDTO);
            return ResponseEntity.ok(updatePlayer);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        try {
            playerService.deletePlayerById(id);
            return ResponseEntity.ok("Player deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // @PostMapping("/joueurs/{id}/amis")

    // // Add a test endpoint
    @GetMapping("/test")
    public String testEndpoint() {
        return "Player service is up and running!";
    }
}