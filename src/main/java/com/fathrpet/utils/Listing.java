package com.fathrpet.utils;

import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;
import com.fathrpet.repositories.MarketplaceRepository;
import com.fathrpet.repositories.PokemonRepository;
import com.fathrpet.repositories.UserRepository;

import java.time.LocalDateTime;

public class Listing {

    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;
    private final MarketplaceRepository marketplaceRepository;

    public Listing(UserRepository userRepository, PokemonRepository pokemonRepository, MarketplaceRepository marketplaceRepository){
        this.userRepository = userRepository;
        this.pokemonRepository = pokemonRepository;
        this.marketplaceRepository = marketplaceRepository;
    }

    private void validateListing(User seller, Pokemon pokemon){
        if(!seller.getInventory().contains(pokemon)){
            throw new IllegalStateException("Pokemon não encontrado no inventário do usuário");
        }

        if(pokemon.isListed()) {
            throw new IllegalStateException("Pokemon jś está listado");
        }
    }

    private Marketplace createNewListing(Pokemon pokemon, User seller, double price){
        Marketplace listing = new Marketplace();
        listing.setPokemon(pokemon);
        listing.setPrice(price);
        listing.setSeller(seller);
        listing.setCreatedListingAt(LocalDateTime.now());
        listing.setSold(false);
        return listing;
    }

    private void updatePokemonListingStatus(Pokemon pokemon){
        pokemon.setListed(true);
    }

    private Marketplace saveAllChanges(Marketplace listing, User seller, Pokemon pokemon){
        pokemonRepository.save(pokemon);
        userRepository.save(seller);
        return marketplaceRepository.save(listing);
    }
}
