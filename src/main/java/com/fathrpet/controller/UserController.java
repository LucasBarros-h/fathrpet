package com.fathrpet.controller;

import com.fathrpet.mappers.MarketplaceMapper;
import com.fathrpet.mappers.PokemonMapper;
import com.fathrpet.mappers.TowerMapper;
import com.fathrpet.mappers.UserMapper;
import com.fathrpet.model.dto.*;
import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;
import com.fathrpet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/v1")
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO){
        User user = UserMapper.toEntity(createUserDTO);
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(createdUser));
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUser().stream().map(UserMapper::toDTO).toList();
    }

    @GetMapping("/tower/{userId}")
    public ResponseEntity<TowerDTO> getUserTower(@PathVariable Long userId){
        return ResponseEntity.ok(TowerMapper.toDTO(userService.getTowerFromId(userId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(UserMapper.toDTO(userService.getUserById(id)));
    }

    @GetMapping("/inventory/{userId}")
    public ResponseEntity<List<PokemonDTO>> getAllPokemonsFromUserInventory(@PathVariable Long userId){
        List<Pokemon> inventory = userService.getAllPokemonsFromInventory(userId);
        return ResponseEntity.ok(inventory.stream().map(PokemonMapper::toDTO).toList());
    }

    @GetMapping("/listings/{userId}")
    public ResponseEntity<List<MarketplaceDTO>> getAllListingsFromUser(@PathVariable Long userId){
        List<Marketplace> userListings = userService.getAllUserListings(userId);
        return ResponseEntity.ok(userListings.stream().map(MarketplaceMapper::toDTO).toList());
    }

    @PostMapping("/list")
    public ResponseEntity<MarketplaceDTO> listPokemon(@RequestParam Long pokemonId, @RequestParam Long sellerId, @RequestParam double price){
        Marketplace listing = userService.listPokemon(pokemonId, sellerId, price);
        return ResponseEntity.ok(MarketplaceMapper.toDTO(listing));
    }

}
