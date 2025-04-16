package com.fathrpet.service;

import com.fathrpet.exception.AlreadyListedException;
import com.fathrpet.exception.ResourceNotFoundException;
import com.fathrpet.mappers.MarketplaceMapper;
import com.fathrpet.mappers.PokemonMapper;
import com.fathrpet.mappers.UserMapper;
import com.fathrpet.model.dto.UserDTO;
import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;
import com.fathrpet.model.entity.Wallet;
import com.fathrpet.repositories.MarketplaceRepository;
import com.fathrpet.repositories.PokemonRepository;
import com.fathrpet.repositories.UserRepository;
import com.fathrpet.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PokemonRepository pokemonRepository;
    private final MarketplaceRepository marketplaceRepository;

    @Transactional
    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        User savedUser = userRepository.save(user);
        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        walletRepository.save(wallet);

        return savedUser;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo id fornecido"));
    }

    public List<Pokemon> getAllPokemonsFromInventory(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return user.getInventory();
    }

    public List<Marketplace> getAllUserListings(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return user.getAllListings();
    }

    @Transactional
    public Marketplace listPokemon(Long pokemonId, Long sellerId, double price){
        if(marketplaceRepository.existsByPokemon_Id(pokemonId)){
            throw new IllegalStateException("Este Pokemon já está listado no marketplace");
        }

        User seller = userRepository.findWithInventoryById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new ResourceNotFoundException("Pokemon não encontrado"));

        validateListing(seller, pokemon);
        seller.getInventory().remove(pokemon);

        Marketplace listing = createNewListing(pokemon, seller, price);

        updatePokemonListingStatus(pokemon);

        seller.getAllListings().add(listing);

        pokemonRepository.save(pokemon);
        marketplaceRepository.save(listing);
        userRepository.save(seller);

        return listing;
    }

    private void validateListing(User seller, Pokemon pokemon){
        if(!seller.getInventory().contains(pokemon)){
            throw new ResourceNotFoundException("Pokemon não encontrado no inventário do usuário");
        }

        if(pokemon.isListed()) {
            throw new AlreadyListedException("Pokemon já está listado");
        }
    }

    private Marketplace createNewListing(Pokemon pokemon, User seller, double price){
        Marketplace listing = new Marketplace();
        listing.setPokemon(pokemon);
        listing.setPrice(price);
        listing.setSeller(seller);
        listing.setCreatedListingAt(LocalDateTime.now());
        listing.setSold(false);
        pokemon.getListings().add(listing);
        pokemon.setListed(true);
        return listing;
    }

    private void updatePokemonListingStatus(Pokemon pokemon){
        pokemon.setListed(true);
    }

}
