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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private UnitType type;
    private int attack;
    private int defense;
    private int health;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private UnitAbility ability;

    private boolean isBase = false;
    //TODO Dropar as tabelas para serem adequadas ao novo padrão de pokemon
    private Integer level;
    private Integer exp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Marketplace> listings = new ArrayList<>();

    @OneToOne(mappedBy = "pokemon")
    private TowerSlot towerSlot;

    private boolean listed;

    public boolean isAlive(){
        return health > 0;
    }

    public void receiveDamage(int damage){
        this.health -= damage;
    }

    public void heal(int amount){
        this.health += amount;
    }

}
