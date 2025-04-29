package com.fathrpet.model.dto;

import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.UnitAbility;
import com.fathrpet.model.entity.UnitType;
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
    private UnitType type;
    private int attack;
    private int defense;
    private int health;
    private UnitAbility ability;
    private boolean isBase;
    private boolean listed;
    private Long ownerId;
    private Integer level;
    private Integer exp;

    public static PokemonDTO fromEntity(Pokemon pokemon){
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getHealth(),
                pokemon.getAbility(),
                pokemon.isBase(),
                pokemon.isListed(),
                pokemon.getOwner() != null ? pokemon.getOwner().getId() : null,
                pokemon.getLevel(),
                pokemon.getExp()

        );
    }
}
