package com.example.player_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.player_service.entity.Friend;

import jakarta.transaction.Transactional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByPlayerIdAndFriendId(Long playerId, Long friendId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Friend f WHERE f.player.id = :playerId OR f.friend.id = :playerId")
    void deleteAllById(Long playerId);
}
