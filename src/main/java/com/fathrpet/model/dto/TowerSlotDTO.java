package com.fathrpet.model.dto;

import com.fathrpet.model.entity.TowerSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TowerSlotDTO {

    private Long id;
    private Long towerId;
    PokemonDTO pokemonDTO;
    UserDTO userDTO;
    LocalDateTime entryTime;
    LocalDateTime minimumExitTime;
    boolean withdrawn;
    boolean canWithdraw;

    public static TowerSlotDTO fromEntity(TowerSlot slot){
        return new TowerSlotDTO(
                slot.getId(),
                slot.getTower().getId(),
                PokemonDTO.fromEntity(slot.getPokemon()),
                UserDTO.fromEntity(slot.getUser()),
                slot.getEntryTime(),
                slot.getMinimumExitTime(),
                slot.isWithdrawn(),
                slot.isWithdrawn() ? false : LocalDateTime.now().isAfter(slot.getMinimumExitTime())
        );
    }
}
