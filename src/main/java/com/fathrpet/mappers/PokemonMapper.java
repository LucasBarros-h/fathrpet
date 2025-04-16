package com.fathrpet.mappers;

import com.fathrpet.model.dto.PokemonDTO;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;

public class PokemonMapper {

    public static PokemonDTO toDTO(Pokemon pokemon){
        if(pokemon == null) return null;
        PokemonDTO dto = new PokemonDTO();
        dto.setId(pokemon.getId());
        dto.setName(pokemon.getName());
        dto.setType(pokemon.getType());
        dto.setStats(pokemon.getStats());
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
        pokemon.setStats(dto.getStats());
        pokemon.setBase(dto.isBase());
        pokemon.setListed(dto.isListed());
        pokemon.setOwner(owner); // precisa analisar gepeto mandou colocar somente dt.setOwnerId()
        return pokemon;
    }
}

