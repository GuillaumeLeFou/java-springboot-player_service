package com.example.player_service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.player_service.entity.Friend;
import com.example.player_service.repository.FriendRepository;

@Component
public class FriendDAO implements IFriendDAO {
    @Autowired
    private FriendRepository friendRepository;

    @Override
    public boolean friendshipExist(Long playerId, Long friendId) {
        return friendRepository.existsByPlayerIdAndFriendId(playerId, friendId);
    }

    @Override
    public Friend save(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public void deleteFriendship(Long playerId) {
        friendRepository.deleteAllById(playerId);
    }
    
}
