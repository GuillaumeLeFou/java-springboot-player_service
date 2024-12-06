package com.example.player_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.player_service.entity.Friend;
import com.example.player_service.entity.Player;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByPlayerIdAndFriendId(Long playerId, Long friendId);
}
