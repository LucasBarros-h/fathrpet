package com.fathrpet.service;

import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;
import com.fathrpet.repositories.MarketplaceRepository;
import com.fathrpet.repositories.PokemonRepository;
import com.fathrpet.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MarketplaceService {

    private final PokemonRepository pokemonRepository;
    private final WalletService walletService;
    private final MarketplaceRepository marketplaceRepository;
    private final UserRepository userRepository;

    @Transactional
    public Marketplace listPokemon(Long pokemonId, Long sellerId, double price){
        User seller = userRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon não encontrado"));

        if(!seller.getInventory().contains(pokemon)){
            throw new RuntimeException("Pokemon não encontrado na lista do usuário");
        }
        seller.getInventory().remove(pokemon);
        userRepository.save(seller);

        Marketplace listing = new Marketplace();
        listing.setPokemon(pokemon);
        listing.setPrice(price);
        listing.setSeller(seller);
        listing.setCreatedListingAt(LocalDateTime.now());

        return marketplaceRepository.save(listing);
    }

    @Transactional
    public Pokemon buyPokemon(Long listingId, Long buyerId){
        Marketplace listing = marketplaceRepository.findById(listingId).orElseThrow(() -> new RuntimeException("Ordem de venda não encontrada"));

        User buyer = userRepository.findById(buyerId).orElseThrow(() -> new RuntimeException("Comprador não encontrado"));

        walletService.transferFunds(buyerId, listing.getSeller().getId(), listing.getPrice());

        Pokemon boughtPokemon = listing.getPokemon();
        boughtPokemon.setOwner(buyer);
        buyer.getInventory().add(boughtPokemon);

        listing.setSold(true);

        marketplaceRepository.save(listing);
        userRepository.save(buyer);

        return pokemonRepository.save(boughtPokemon);
    }
}
