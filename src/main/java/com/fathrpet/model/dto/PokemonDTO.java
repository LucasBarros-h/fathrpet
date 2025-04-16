package com.fathrpet.model.dto;

import com.fathrpet.model.entity.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTO {
    private Long id;
    private String name;
    private String type;
    private List<Integer> stats;
    private boolean isBase;
    private boolean listed;
    private Long ownerId;
    private int level;
    private int exp;

    public static PokemonDTO fromEntity(Pokemon pokemon){
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType(),
                pokemon.getStats(),
                pokemon.isBase(),
                pokemon.isListed(),
                pokemon.getOwner() != null ? pokemon.getOwner().getId() : null,
                pokemon.getLevel(),
                pokemon.getExp()

        );
    }
}
