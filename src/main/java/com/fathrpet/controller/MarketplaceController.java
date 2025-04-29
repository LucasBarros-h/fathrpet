package com.fathrpet.controller;

import com.fathrpet.mappers.MarketplaceMapper;
import com.fathrpet.mappers.PokemonMapper;
import com.fathrpet.model.dto.MarketplaceDTO;
import com.fathrpet.model.dto.PokemonDTO;
import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.service.MarketplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/marketplace/v1")
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    @PostMapping("/buy/{listingId}")
    public ResponseEntity<PokemonDTO> buyPokemon(@PathVariable Long listingId, @RequestParam Long buyerId){
        Pokemon pokemon = marketplaceService.buyPokemon(listingId, buyerId);
        return ResponseEntity.ok(PokemonMapper.toDTO(pokemon));
    }

    @DeleteMapping("/remove/{listingId}")
    public ResponseEntity<PokemonDTO> removeFromListing(@PathVariable Long listingId){
        Pokemon pokemon = marketplaceService.removeFromListing(listingId);
        return ResponseEntity.ok(PokemonMapper.toDTO(pokemon));

    }

    @GetMapping("/listings")
    public ResponseEntity<List<MarketplaceDTO>> getAllListings(){
        List<Marketplace> listings = marketplaceService.findAllListings();
        return ResponseEntity.ok(listings.stream().map(MarketplaceMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/listing/{listingId}")
    public ResponseEntity<MarketplaceDTO> getListingById(@PathVariable Long listingId){
        Marketplace list = marketplaceService.findListingById(listingId);
        return ResponseEntity.ok(MarketplaceMapper.toDTO(list));
    }

}
