package com.fathrpet.controller;

import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    @PostMapping("/list")
    public ResponseEntity<Marketplace> listPokemon(@RequestParam Long pokemonId, @RequestParam Long sellerId, @RequestBody double price){
        Marketplace listing = marketplaceService.listPokemon(pokemonId, sellerId, price);
        return ResponseEntity.ok(listing);
    }

    public ResponseEntity<Pokemon> buyPokemon(@PathVariable Long listingId, @RequestParam Long buyerId){
        Pokemon pokemon = marketplaceService.buyPokemon(listingId, buyerId);
        return ResponseEntity.ok(pokemon);
    }

}
