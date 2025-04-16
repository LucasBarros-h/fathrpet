package com.fathrpet.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LeavePokemonRequestDTO(@NotNull Long towerId, @NotNull Long pokemonId, @NotNull Long userId, @Min(1) int minutes) {
}
