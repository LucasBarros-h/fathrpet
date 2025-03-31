package com.fathrpet.model.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne(mappedBy = "pokemon")
    private Marketplace listPosition;

}
