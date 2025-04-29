package com.fathrpet.repositories;

import com.fathrpet.model.entity.TowerSlot;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TowerSlotRepository extends JpaRepository<TowerSlot, Long> {
    @EntityGraph(attributePaths = {"pokemon", "user"})
    Optional<TowerSlot> findWithPokemonAndUserById(Long id);

    List<TowerSlot> findByUser_IdAndWithdrawnFalse(Long userId);
}
