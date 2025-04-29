package com.fathrpet.controller;

import com.fathrpet.mappers.PokemonMapper;
import com.fathrpet.model.dto.PokemonDTO;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pokemon/v1")
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/base")
    public ResponseEntity<List<PokemonDTO>> getBasePokemons(){
        List<Pokemon> basePokemons = pokemonService.getBasePokemons();
        return ResponseEntity.ok(basePokemons.stream().map(PokemonMapper::toDTO).collect(Collectors.toList()));
    }

    @PostMapping("/generate/{userId}")
    public ResponseEntity<PokemonDTO> generatePokemon(@PathVariable Long userId){
        Pokemon generatedPokemon = pokemonService.generatePokemon(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(PokemonMapper.toDTO(generatedPokemon));
    }
 }

