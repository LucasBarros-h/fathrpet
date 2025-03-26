package com.fathrpet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;

    @ElementCollection
    @CollectionTable(name = "pokemon_status", joinColumns = @JoinColumn(name = "pokemon_id"))
    private List<Integer> status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;

    @OneToOne(mappedBy = "pokemon", cascade = CascadeType.ALL)
    @JsonIgnore
    private Marketplace marketplaceListing;

}
