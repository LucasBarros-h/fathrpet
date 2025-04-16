package com.fathrpet.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletDTO {
    private Long id;
    private Double balance;
    private Long userId;
}
