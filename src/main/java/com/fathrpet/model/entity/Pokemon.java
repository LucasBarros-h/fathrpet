package com.fathrpet.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ElementCollection
    private List<Integer> stats;

    private boolean isBase = false;

    private int level = 1;
    private int exp = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Marketplace> listings = new ArrayList<>();

    @OneToOne(mappedBy = "pokemonInSlot")
    private TowerSlot towerSlot;

    private boolean listed;

}
