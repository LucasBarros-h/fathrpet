package com.fathrpet.model.dto;

import com.fathrpet.model.entity.Tower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TowerDTO {

    private Long id;
    private String name;
    private int capacity;
    List<TowerSlotDTO> slots;
    private Long ownerId;

    public static TowerDTO fromEntity(Tower tower){
        return new TowerDTO(
                tower.getId(),
                tower.getName(),
                tower.getCapacity(),
                tower.getSlots().stream().map(TowerSlotDTO::fromEntity).toList(),
                tower.getOwner().getId()
        );
    }
}
