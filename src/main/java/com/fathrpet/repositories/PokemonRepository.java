package com.fathrpet.repositories;

import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByIsBaseTrue();
    List<Pokemon> findOwnerById(Long ownerId);
    List<Pokemon> findByTypeAndIsBaseTrue(UnitType type);

}
