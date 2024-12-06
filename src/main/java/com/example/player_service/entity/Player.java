package com.example.player_service.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String pseudo;

    @Email(message = "Invalide email address")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int niveau = 1;
    
    @Column(nullable = false)
    private int totalPoints = 0;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Set<Friend> friends;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
    private Set<Friend> friendOf;
}
