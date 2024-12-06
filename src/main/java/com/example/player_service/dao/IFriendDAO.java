package com.example.player_service.dao;

import com.example.player_service.entity.Friend;

public interface IFriendDAO {
    Friend save(Friend friend);
    boolean friendshipExist(Long playerId, Long friendId);
}
