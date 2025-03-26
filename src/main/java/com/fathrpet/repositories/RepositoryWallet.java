package com.fathrpet.repositories;

import com.fathrpet.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryWallet extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUserWalletId(Long userWalletId);
}
