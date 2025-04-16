package com.fathrpet.mappers;

import com.fathrpet.model.dto.WalletDTO;
import com.fathrpet.model.entity.User;
import com.fathrpet.model.entity.Wallet;

public class WalletMapper {

    public static WalletDTO toDTO(Wallet wallet){
        if(wallet == null) return null;
        WalletDTO dto = new WalletDTO();
        dto.setId(wallet.getId());
        dto.setBalance(wallet.getBalance());
        dto.setUserId(wallet.getUser() != null ? wallet.getUser().getId() : null);
        return dto;
    }

    public static Wallet toEntity(WalletDTO dto, User user){
        if(dto == null) return null;
        Wallet wallet = new Wallet();
        wallet.setId(dto.getId());
        wallet.setBalance(dto.getBalance());
        wallet.setUser(user);
        return wallet;
    }
}
