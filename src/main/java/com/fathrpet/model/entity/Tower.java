package com.fathrpet.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;

    @OneToMany(mappedBy = "tower", cascade = CascadeType.ALL)
    private List<TowerSlot> slots = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
