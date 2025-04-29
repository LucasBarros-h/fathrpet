package com.fathrpet.service;

import com.fathrpet.exception.AlreadyListedException;
import com.fathrpet.exception.ResourceNotFoundException;
import com.fathrpet.mappers.MarketplaceMapper;
import com.fathrpet.mappers.PokemonMapper;
import com.fathrpet.mappers.UserMapper;
import com.fathrpet.model.dto.UserDTO;
import com.fathrpet.model.entity.*;
import com.fathrpet.repositories.*;
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
    private final TowerRepository towerRepository;

    @Transactional
    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if(user.getPassword() == null){
            throw new RuntimeException("Senha inválida");
        }
        User savedUser = userRepository.save(user);
        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        walletRepository.save(wallet);
        Tower tower = new Tower();
        tower.setOwner(savedUser);
        tower.setName(savedUser.getUsername() + "'s Tower");
        tower.setCapacity(3);
        towerRepository.save(tower);

        return savedUser;
    }

    public Tower getTowerFromId(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return user.getTower();
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
        pokemon.setListed(true);

        Marketplace listing = new Marketplace();
        listing.setPokemon(pokemon);
        listing.setSeller(seller);
        listing.setPrice(price);
        listing.setCreatedListingAt(LocalDateTime.now());
        listing.setSold(false);

        seller.getInventory().remove(pokemon);
        seller.getAllListings().add(listing);
        pokemon.getListings().add(listing);

        return marketplaceRepository.save(listing);
    }

    private void validateListing(User seller, Pokemon pokemon){
        if(!seller.getInventory().contains(pokemon)){
            throw new ResourceNotFoundException("Pokemon não encontrado no inventário do usuário");
        }

        if(pokemon.isListed()) {
            throw new AlreadyListedException("Pokemon já está listado");
        }
    }

}
