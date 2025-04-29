package com.fathrpet.mappers;

import com.fathrpet.model.dto.PokemonDTO;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;
import org.springframework.stereotype.Component;

public class PokemonMapper {

    public static PokemonDTO toDTO(Pokemon pokemon){
        if(pokemon == null) return null;
        PokemonDTO dto = new PokemonDTO();
        dto.setId(pokemon.getId());
        dto.setName(pokemon.getName());
        dto.setType(pokemon.getType());
        dto.setAttack(pokemon.getAttack());
        dto.setDefense(pokemon.getDefense());
        dto.setHealth(pokemon.getHealth());
        dto.setAbility(pokemon.getAbility());
        dto.setLevel(pokemon.getLevel());
        dto.setExp(pokemon.getExp());
        dto.setBase(pokemon.isBase());
        dto.setListed(pokemon.isListed());
        dto.setOwnerId(pokemon.getOwner() != null ? pokemon.getOwner().getId() : null);
        return dto;
        }

    public static Pokemon toEntity(PokemonDTO dto, User owner){
        if(dto == null) return null;
        Pokemon pokemon = new Pokemon();
        pokemon.setId(dto.getId());
        pokemon.setName(dto.getName());
        pokemon.setType(dto.getType());
        pokemon.setAttack(dto.getAttack());
        pokemon.setDefense(dto.getDefense());
        pokemon.setHealth(dto.getHealth());
        pokemon.setAbility(dto.getAbility());
        pokemon.setLevel(dto.getLevel());
        pokemon.setExp(dto.getExp());
        pokemon.setBase(dto.isBase());
        pokemon.setListed(dto.isListed());
        pokemon.setOwner(owner); // precisa analisar gepeto mandou colocar somente dt.setOwnerId()
        return pokemon;
    }
}

