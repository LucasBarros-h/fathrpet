package com.fathrpet.service;

import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;
import com.fathrpet.repositories.PokemonRepository;
import com.fathrpet.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepository;
    private final Random random;

    @Transactional
    public Pokemon generatePokemon(Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Pokemon> basePokemons = pokemonRepository.findByIsBaseTrue();
        if(basePokemons.isEmpty()) {
            throw new IllegalStateException("Não há pokemons base cadastrados no sistema.");
        }
        Pokemon base = basePokemons.get(random.nextInt(basePokemons.size()));

        Pokemon generatedPokemon = createPokeVariant(base);
        generatedPokemon.setOwner(owner);
        owner.getInventory().add(generatedPokemon);

        return pokemonRepository.save(generatedPokemon);
    }

    public Pokemon createPokeVariant(Pokemon basePokemon){
        Pokemon pokeVariant = new Pokemon();
        pokeVariant.setName(basePokemon.getName());
        pokeVariant.setType(basePokemon.getType());
        pokeVariant.setBase(false);
        pokeVariant.setStats(basePokemon.getStats().stream().map(stat -> stat + random.nextInt(11) - 5).toList());

        return pokeVariant;
    }

    public List<Pokemon> getBasePokemons(){
        return pokemonRepository.findByIsBaseTrue();
    }

    public List<Pokemon> getUserPokemons(Long userId){
        return pokemonRepository.findOwnerById(userId);
    }

}
