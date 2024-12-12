package com.example.player_service;

import com.example.player_service.dao.IFriendDAO;
import com.example.player_service.dao.IPlayerDAO;
import com.example.player_service.dto.PlayerCreateDTO;
import com.example.player_service.dto.PlayerUpdateDTO;
import com.example.player_service.entity.Player;
import com.example.player_service.services.PlayerService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private IPlayerDAO playerDAO;

    @Mock
    private IFriendDAO friendDAO;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testRegisterPlayer_Success() {
        PlayerCreateDTO playerCreateDTO = new PlayerCreateDTO();
        playerCreateDTO.setName("John Doe");
        playerCreateDTO.setPseudo("PlayerOne");
        playerCreateDTO.setEmail("john.doe@example.com");

        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");
        player.setPseudo("PlayerOne");
        player.setEmail("john.doe@example.com");

        when(playerDAO.save(Mockito.any(Player.class))).thenReturn(player);

        Player result = playerService.registerPlayer(playerCreateDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("PlayerOne", result.getPseudo());
        assertEquals("john.doe@example.com", result.getEmail());

        verify(playerDAO, times(1)).save(Mockito.any(Player.class));
    }

    @Test
    public void testGetPlayerById_PlayerExists() {
        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");

        when(playerDAO.findById(1L)).thenReturn(Optional.of(player));

        Player result = playerService.getPlayerById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());

        verify(playerDAO, times(1)).findById(1L);
    }

    @Test
    public void testGetPlayerById_PlayerNotFound() {
        when(playerDAO.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.getPlayerById(1L);
        });

        assertEquals("Player not found", exception.getMessage());

        verify(playerDAO, times(1)).findById(1L);
    }

    @Test
    public void testUpdatePlayer_Success() {
        PlayerUpdateDTO playerUpdateDTO = new PlayerUpdateDTO();
        playerUpdateDTO.setName("Updated Name");
        playerUpdateDTO.setPseudo("UpdatedPseudo");
        playerUpdateDTO.setEmail("updated@example.com");

        Player existingPlayer = new Player();
        existingPlayer.setId(1L);
        existingPlayer.setName("John Doe");
        existingPlayer.setPseudo("PlayerOne");
        existingPlayer.setEmail("john.doe@example.com");

        Player updatedPlayer = new Player();
        updatedPlayer.setId(1L);
        updatedPlayer.setName("Updated Name");
        updatedPlayer.setPseudo("UpdatedPseudo");
        updatedPlayer.setEmail("updated@example.com");

        when(playerDAO.findById(1L)).thenReturn(Optional.of(existingPlayer));
        when(playerDAO.save(Mockito.any(Player.class))).thenReturn(updatedPlayer);
        when(playerDAO.emailExists("updated@example.com")).thenReturn(false);
        when(playerDAO.pseudoExists("UpdatedPseudo")).thenReturn(false);

        Player result = playerService.updatePlayer(1L, playerUpdateDTO);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals("UpdatedPseudo", result.getPseudo());
        assertEquals("updated@example.com", result.getEmail());

        verify(playerDAO, times(1)).findById(1L);
        verify(playerDAO, times(1)).save(Mockito.any(Player.class));
    }

    @Test
    public void testDeletePlayerById_PlayerExists() {
        when(playerDAO.existsById(1L)).thenReturn(true);
        doNothing().when(friendDAO).deleteFriendship(1L);
        doNothing().when(playerDAO).deletePlayerById(1L);

        playerService.deletePlayerById(1L);

        verify(playerDAO, times(1)).existsById(1L);
        verify(friendDAO, times(1)).deleteFriendship(1L);
        verify(playerDAO, times(1)).deletePlayerById(1L);
    }

    @Test
    public void testDeletePlayerById_PlayerNotFound() {
        when(playerDAO.existsById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            playerService.deletePlayerById(1L);
        });

        assertEquals("Player not found by id", exception.getMessage());

        verify(playerDAO, times(1)).existsById(1L);
        verify(friendDAO, never()).deleteFriendship(anyLong());
        verify(playerDAO, never()).deletePlayerById(anyLong());
    }
}
