package com.fathrpet.service;

import com.fathrpet.model.entity.Wallet;
import com.fathrpet.repositories.UserRepository;
import com.fathrpet.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public void addFunds(Long userId, double amount){
        Wallet userWallet = walletRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        userWallet.setBalance(userWallet.getBalance() + amount);
        walletRepository.save(userWallet);
    }

    @Transactional
    public void transferFunds(Long fromUserId, Long toUserId, double amount){
        Wallet fromUser = walletRepository.findByUserId(fromUserId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Wallet toUser = walletRepository.findByUserId(toUserId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(fromUser.getBalance() < amount){
            throw new RuntimeException("Saldo insuficiente");
        }
        fromUser.setBalance(fromUser.getBalance() - amount);
        toUser.setBalance(toUser.getBalance() + amount);

        walletRepository.save(fromUser);
        walletRepository.save(toUser);
    }

}
