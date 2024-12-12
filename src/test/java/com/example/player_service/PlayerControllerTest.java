package com.example.player_service;

import com.example.player_service.controller.PlayerController;
import com.example.player_service.dto.*;
import com.example.player_service.entity.Friend;
import com.example.player_service.entity.Player;
import com.example.player_service.services.IFriendService;
import com.example.player_service.services.IPlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPlayerService playerService;

    @MockBean
    private IFriendService friendService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterPlayer() throws Exception {
        PlayerCreateDTO playerCreateDTO = new PlayerCreateDTO();
        playerCreateDTO.setName("Player Name");
        playerCreateDTO.setPseudo("PlayerOne");
        playerCreateDTO.setEmail("playerone@example.com");

        Player player = new Player();
        player.setId(1L);
        player.setName("Player Name");
        player.setPseudo("PlayerOne");
        player.setEmail("playerone@example.com");
        player.setNiveau(1);
        player.setTotalPoints(0);

        when(playerService.registerPlayer(Mockito.any(PlayerCreateDTO.class))).thenReturn(player);

        mockMvc.perform(post("/Player/addPlayer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Player Name"))
                .andExpect(jsonPath("$.pseudo").value("PlayerOne"))
                .andExpect(jsonPath("$.email").value("playerone@example.com"))
                .andExpect(jsonPath("$.niveau").value(1))
                .andExpect(jsonPath("$.totalPoints").value(0));

        verify(playerService, times(1)).registerPlayer(Mockito.any(PlayerCreateDTO.class));
    }

    @Test
    public void testGetPlayerById() throws Exception {
        Player player = new Player();
        player.setId(1L);
        player.setPseudo("PlayerOne");
        player.setEmail("playerone@example.com");
        player.setNiveau(5);
        player.setTotalPoints(1200);

        when(playerService.getPlayerById(1L)).thenReturn(player);

        mockMvc.perform(get("/Player/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.pseudo").value("PlayerOne"))
                .andExpect(jsonPath("$.email").value("playerone@example.com"))
                .andExpect(jsonPath("$.niveau").value(5))
                .andExpect(jsonPath("$.totalPoints").value(1200));

        verify(playerService, times(1)).getPlayerById(1L);
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        PlayerUpdateDTO playerUpdateDTO = new PlayerUpdateDTO();
        playerUpdateDTO.setPseudo("UpdatedPlayer");

        Player updatedPlayer = new Player();
        updatedPlayer.setId(1L);
        updatedPlayer.setPseudo("UpdatedPlayer");
        updatedPlayer.setEmail("updated@example.com");
        updatedPlayer.setNiveau(6);
        updatedPlayer.setTotalPoints(1500);

        when(playerService.updatePlayer(eq(1L), Mockito.any(PlayerUpdateDTO.class))).thenReturn(updatedPlayer);

        mockMvc.perform(put("/Player/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.pseudo").value("UpdatedPlayer"))
                .andExpect(jsonPath("$.email").value("updated@example.com"))
                .andExpect(jsonPath("$.niveau").value(6))
                .andExpect(jsonPath("$.totalPoints").value(1500));

        verify(playerService, times(1)).updatePlayer(eq(1L), Mockito.any(PlayerUpdateDTO.class));
    }

    @Test
    public void testAddFriendship() throws Exception {
        AddFriendDTO addFriendDTO = new AddFriendDTO();
        addFriendDTO.setPlayer(1L);
        addFriendDTO.setFriend(2L);
    
        Player player = new Player();
        player.setId(1L);
        player.setName("Player One");
        player.setPseudo("PlayerOne");
        player.setEmail("playerone@example.com");
        player.setNiveau(5);
        player.setTotalPoints(1500);
        player.setFriends(Collections.emptyList()); 
    
        Player friendPlayer = new Player();
        friendPlayer.setId(2L);
        friendPlayer.setName("Player Two");
        friendPlayer.setPseudo("PlayerTwo");
        friendPlayer.setEmail("playertwo@example.com");
        friendPlayer.setNiveau(3);
        friendPlayer.setTotalPoints(900);
        friendPlayer.setFriends(Collections.emptyList());
    
        Friend friend = new Friend();
        friend.setId(10L);
        friend.setPlayer(player);
        friend.setFriend(friendPlayer);
    
        when(friendService.addFriendship(Mockito.any(AddFriendDTO.class))).thenReturn(friend);
    
        mockMvc.perform(post("/Player/addFriendship")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addFriendDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.player.id").value(1L))
                .andExpect(jsonPath("$.player.name").value("Player One"))
                .andExpect(jsonPath("$.player.pseudo").value("PlayerOne"))
                .andExpect(jsonPath("$.player.email").value("playerone@example.com"))
                .andExpect(jsonPath("$.player.niveau").value(5))
                .andExpect(jsonPath("$.player.totalPoints").value(1500))
                .andExpect(jsonPath("$.friend.id").value(2L))
                .andExpect(jsonPath("$.friend.name").value("Player Two"))
                .andExpect(jsonPath("$.friend.pseudo").value("PlayerTwo"))
                .andExpect(jsonPath("$.friend.email").value("playertwo@example.com"))
                .andExpect(jsonPath("$.friend.niveau").value(3))
                .andExpect(jsonPath("$.friend.totalPoints").value(900));
    
        verify(friendService, times(1)).addFriendship(Mockito.any(AddFriendDTO.class));
    }
}
