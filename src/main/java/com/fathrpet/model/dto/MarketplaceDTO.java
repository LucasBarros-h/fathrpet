package com.fathrpet.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MarketplaceDTO {
    private Long id;
    private Long pokemonId;
    private Long sellerId;
    private double price;
    private LocalDateTime createdListingAt;
    private boolean isSold;
}
