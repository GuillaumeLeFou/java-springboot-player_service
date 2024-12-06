package com.example.player_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.player_service.dao.IFriendDAO;
import com.example.player_service.dao.IPlayerDAO;
import com.example.player_service.dto.AddFriendDTO;
import com.example.player_service.dto.PlayerDTO;
import com.example.player_service.entity.Friend;
import com.example.player_service.entity.Player;

@Service
public class FriendService implements IFriendService{
    @Autowired
    private IPlayerDAO playerDAO;

    @Autowired
    private IFriendDAO friendDAO;


    @Override
    public Friend addFriendship(AddFriendDTO addFriendDTO) {
        Player player = playerDAO.findById(addFriendDTO.getPlayer()).orElse(null);
        Player friend = playerDAO.findById(addFriendDTO.getFriend()).orElse(null);
        
        if(player == null || friend == null){
            throw new IllegalArgumentException("one player doesn't exist");
        }

        if (friendDAO.friendshipExist(addFriendDTO.getPlayer(), addFriendDTO.getFriend()) ||
            friendDAO.friendshipExist(addFriendDTO.getFriend(), addFriendDTO.getPlayer())) {
            throw new IllegalArgumentException("you are already friend");
        }

        if(player.getId() == friend.getId()){
            throw new IllegalArgumentException("you can't add you");
        }

        Friend friendship = new Friend();
        friendship.setPlayer(player);
        friendship.setFriend(friend);
        return friendDAO.save(friendship);
    }
    
}
