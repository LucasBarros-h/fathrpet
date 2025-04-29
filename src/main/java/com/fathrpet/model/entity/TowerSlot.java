package com.fathrpet.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TowerSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tower_id")
    private Tower tower;

    @OneToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime entryTime;
    private LocalDateTime minimumExitTime;

    private boolean withdrawn;

}
