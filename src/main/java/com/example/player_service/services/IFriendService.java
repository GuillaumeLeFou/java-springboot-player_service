package com.example.player_service.services;

import com.example.player_service.dto.AddFriendDTO;
import com.example.player_service.entity.Friend;

public interface IFriendService {
    Friend addFriendship(AddFriendDTO addFriendDTO);
}
