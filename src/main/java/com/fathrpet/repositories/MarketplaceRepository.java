package com.fathrpet.repositories;

import com.fathrpet.model.entity.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {
    List<Marketplace> findByIsSoldFalse();
    List<Marketplace> findBySellerIdAndIsSoldFalse(Long sellerId);

}
