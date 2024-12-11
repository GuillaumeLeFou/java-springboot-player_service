package com.example.player_service.controller;

import com.example.player_service.dto.AddFriendDTO;
import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.dto.PlayerUpdateDTO;
import com.example.player_service.entity.Friend;
import com.example.player_service.entity.Player;
import com.example.player_service.services.IFriendService;
import com.example.player_service.services.IPlayerService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @Autowired
    private IFriendService friendService;

    @PostMapping("/addPlayer")
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

    @GetMapping("/id/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        try {
            Player player = playerService.getPlayerById(id);
            return ResponseEntity.ok(player);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerUpdateDTO playerUpdateDTO){
        try{
            Player updatePlayer = playerService.updatePlayer(id, playerUpdateDTO);
            return ResponseEntity.ok(updatePlayer);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        try {
            playerService.deletePlayerById(id);
            return ResponseEntity.ok("Player deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/addFriendship")
    public ResponseEntity<Friend> newFriendship(
        @Valid @RequestBody AddFriendDTO friendDTO
    ){
        try {
            Friend friend = friendService.addFriendship(friendDTO);
            return ResponseEntity.ok(friend);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // @PostMapping("/joueurs/{id}/amis")

    // Add a test endpoint
    @GetMapping("/test")
    public String testEndpoint() {
        return "Player service is up and running!";
    }
}