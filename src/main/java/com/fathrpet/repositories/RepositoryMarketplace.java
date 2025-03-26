package com.fathrpet.repositories;

import com.fathrpet.model.entity.Marketplace;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryMarketplace {
    List<Marketplace> findByIsSoldFalse();
    List<Marketplace> findBySellerAndIsSoldFalse(Long sellerId);
    Optional<Marketplace> findByPokemonId(Long pokemonId);
    boolean existsByPokemonIdAndIsSoldFalse(Long pokemonId);
    List<Marketplace> findBySellerId(Long sellerId);
}
