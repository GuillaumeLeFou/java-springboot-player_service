package com.example.player_service;

import com.example.player_service.dao.IFriendDAO;
import com.example.player_service.dao.IPlayerDAO;
import com.example.player_service.dto.AddFriendDTO;
import com.example.player_service.entity.Friend;
import com.example.player_service.entity.Player;
import com.example.player_service.services.FriendService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class FriendServiceTest {

    @Mock
    private IPlayerDAO playerDAO;

    @Mock
    private IFriendDAO friendDAO;

    @InjectMocks
    private FriendService friendService;

    @Test
    public void testAddFriendship_Success() {
        AddFriendDTO addFriendDTO = new AddFriendDTO();
        addFriendDTO.setPlayer(1L);
        addFriendDTO.setFriend(2L);

        Player player = new Player();
        player.setId(1L);
        player.setPseudo("PlayerOne");

        Player friend = new Player();
        friend.setId(2L);
        friend.setPseudo("PlayerTwo");

        Friend friendship = new Friend();
        friendship.setPlayer(player);
        friendship.setFriend(friend);

        when(playerDAO.findById(1L)).thenReturn(Optional.of(player));
        when(playerDAO.findById(2L)).thenReturn(Optional.of(friend));
        when(friendDAO.friendshipExist(1L, 2L)).thenReturn(false);
        when(friendDAO.friendshipExist(2L, 1L)).thenReturn(false);
        when(friendDAO.save(any(Friend.class))).thenReturn(friendship);

        Friend result = friendService.addFriendship(addFriendDTO);

        assertNotNull(result);
        assertEquals(1L, result.getPlayer().getId());
        assertEquals(2L, result.getFriend().getId());

        verify(playerDAO, times(1)).findById(1L);
        verify(playerDAO, times(1)).findById(2L);
        verify(friendDAO, times(1)).friendshipExist(1L, 2L);
        verify(friendDAO, times(1)).save(any(Friend.class));
    }

    @Test
    public void testAddFriendship_PlayerNotFound() {
        AddFriendDTO addFriendDTO = new AddFriendDTO();
        addFriendDTO.setPlayer(1L);
        addFriendDTO.setFriend(2L);

        when(playerDAO.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            friendService.addFriendship(addFriendDTO);
        });

        assertEquals("Player doesn't exist", exception.getMessage());

        verify(playerDAO, times(1)).findById(1L);
        verify(playerDAO, never()).findById(2L);
        verify(friendDAO, never()).friendshipExist(anyLong(), anyLong());
        verify(friendDAO, never()).save(any(Friend.class));
    }


    @Test
    public void testAddFriendship_AlreadyFriends() {
        AddFriendDTO addFriendDTO = new AddFriendDTO();
        addFriendDTO.setPlayer(1L);
        addFriendDTO.setFriend(2L);

        Player player = new Player();
        player.setId(1L);

        Player friend = new Player();
        friend.setId(2L);

        when(playerDAO.findById(1L)).thenReturn(Optional.of(player));
        when(playerDAO.findById(2L)).thenReturn(Optional.of(friend));
        when(friendDAO.friendshipExist(1L, 2L)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            friendService.addFriendship(addFriendDTO);
        });

        assertEquals("you are already friend", exception.getMessage());

        verify(playerDAO, times(1)).findById(1L);
        verify(playerDAO, times(1)).findById(2L);
        verify(friendDAO, times(1)).friendshipExist(1L, 2L);
        verify(friendDAO, never()).save(any(Friend.class));
    }

    @Test
public void testAddFriendship_AddYourself() {
    AddFriendDTO addFriendDTO = new AddFriendDTO();
    addFriendDTO.setPlayer(1L);
    addFriendDTO.setFriend(1L);

    Player player = new Player();
    player.setId(1L);
    player.setName("Player One");

    when(playerDAO.findById(1L)).thenReturn(Optional.of(player));

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        friendService.addFriendship(addFriendDTO);
    });

    assertEquals("you can't add yourself as a friend", exception.getMessage());

    verify(playerDAO, times(2)).findById(1L);
    verify(friendDAO, never()).friendshipExist(anyLong(), anyLong());
    verify(friendDAO, never()).save(any(Friend.class));
}
}
