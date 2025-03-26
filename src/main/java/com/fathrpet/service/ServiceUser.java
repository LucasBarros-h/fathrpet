package com.fathrpet.service;

import com.fathrpet.model.entity.User;
import com.fathrpet.model.entity.Wallet;
import com.fathrpet.repositories.RepositoryPokemon;
import com.fathrpet.repositories.RepositoryUser;
import com.fathrpet.repositories.RepositoryWallet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final RepositoryUser userRepository;
    //private final RepositoryPokemon pokemonRepository;
    private final RepositoryWallet walletRepository;
    //private final ServicePokemon pokemonService;
    //private final ServiceMarketplace marketplaceService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
           throw new RuntimeException("Email já existente no nosso banco");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        Wallet userWallet = new Wallet();
        userWallet.setUserWallet(savedUser);
        userWallet.setAmount(0.0);
        walletRepository.save(userWallet);

        return savedUser;
    }

    @Transactional
    public void addFunds(Long userId, Double amount) {
        if(amount <= 0) {
            throw new RuntimeException("Saldo inválido");
        }

        Wallet wallet = walletRepository.findByUserWalletId(userId).orElseThrow(() -> new RuntimeException("Carteira não encontrada"));
        wallet.setAmount(wallet.getAmount() + amount);
        walletRepository.save(wallet);

    }

}
