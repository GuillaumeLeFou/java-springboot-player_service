package com.example.player_service.controller;

import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.dto.PlayerProfileDTO;
import com.example.player_service.dto.PlayerUpdateDTO;
import com.example.player_service.entity.Player;
import com.example.player_service.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerProfileDTO> createPlayer(
            @Valid @RequestBody PlayerCreateDTO playerDTO) {
        PlayerProfileDTO createdPlayer = playerService.createPlayer(playerDTO);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    // à essayer
    @GetMapping("/{id}")
    public ResponseEntity<PlayerProfileDTO> getPlayerById(@PathVariable Long id) {
        PlayerProfileDTO player = playerService.getPlayerById(id);
        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Add a test endpoint
    @GetMapping("/test")
    public String testEndpoint() {
        return "Player service is up and running!";
    }
}