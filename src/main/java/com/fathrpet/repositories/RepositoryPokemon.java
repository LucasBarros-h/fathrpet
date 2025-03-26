package com.fathrpet.repositories;

import com.fathrpet.model.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryPokemon extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByOwnerId(Long ownerId);
    Optional<Pokemon> findByIdAndOwnerId(Long id, Long ownerId);
    boolean existsByIdAndOwnerId(Long id, Long ownerId);


}
