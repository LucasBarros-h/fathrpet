package com.fathrpet.controller;

import com.fathrpet.model.entity.Wallet;
import com.fathrpet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<String> addFunds(@PathVariable Long userId, @RequestParam double amount){
        walletService.addFunds(userId, amount);
        return ResponseEntity.ok("Saldo adicionado com sucesso");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestParam Long fromUserId, @RequestParam Long toUserId, @RequestParam double amount){
        walletService.transferFunds(fromUserId, toUserId, amount);
        return ResponseEntity.ok("Saldo transferido com sucesso");
    }
}
