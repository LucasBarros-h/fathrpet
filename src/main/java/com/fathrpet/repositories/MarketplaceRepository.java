package com.fathrpet.repositories;

import com.fathrpet.model.entity.Marketplace;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;
import java.util.Optional;

public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {
    List<Marketplace> findByIsSoldFalse();
    List<Marketplace> findBySellerIdAndIsSoldFalse(Long sellerId);

    @EntityGraph(attributePaths = {"pokemon", "pokemon.owner", "seller"})
    Optional<Marketplace> findWithPokemonAndSellerById(Long id);

    boolean existsByPokemon_Id(Long pokemonId);

    @EntityGraph(attributePaths = {"pokemon", "seller"})
    Optional<Marketplace> findByPokemonId(Long pokemonId);

    @Modifying
    @Query("DELETE FROM Marketplace m WHERE m.id = :id")
    void deleteByIdCustom(Long id);

}
