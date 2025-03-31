package com.fathrpet.controller;

import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/base")
    public List<Pokemon> getBasePokemons(){
        return pokemonService.getBasePokemons();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Pokemon>> getUserPokemons(@PathVariable Long userId){
        List<Pokemon> pokemons = pokemonService.getUserPokemons(userId);
        if(pokemons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pokemons);
        }
        return ResponseEntity.ok(pokemons);
    }

    @PostMapping("/generate/{userId}")
    public ResponseEntity<Pokemon> generatePokemon(@PathVariable Long userId){
        Pokemon generatedPokemon = pokemonService.generatePokemon(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(generatedPokemon);
    }
 }

