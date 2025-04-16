package com.fathrpet.mappers;

import com.fathrpet.model.dto.MarketplaceDTO;
import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;

public class MarketplaceMapper {

    public static MarketplaceDTO toDTO(Marketplace marketplace){
        if(marketplace == null) return null;
        MarketplaceDTO dto = new MarketplaceDTO();
        dto.setId(marketplace.getId());
        dto.setPokemonId(marketplace.getPokemon() != null ? marketplace.getPokemon().getId() : null);
        dto.setSellerId(marketplace.getSeller() != null ? marketplace.getSeller().getId() : null);
        dto.setPrice(marketplace.getPrice());
        dto.setCreatedListingAt(marketplace.getCreatedListingAt());
        dto.setSold(marketplace.isSold());
        return dto;
    }

    public static Marketplace toEntity(MarketplaceDTO dto, Pokemon pokemon, User seller){
        if(dto == null) return null;
        Marketplace marketplace = new Marketplace();
        marketplace.setId(dto.getId());
        marketplace.setPokemon(pokemon);
        marketplace.setSeller(seller);
        marketplace.setPrice(dto.getPrice());
        marketplace.setCreatedListingAt(dto.getCreatedListingAt());
        marketplace.setSold(dto.isSold());
        return marketplace;
    }
}
