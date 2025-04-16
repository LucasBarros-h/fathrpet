package com.fathrpet.mappers;

import com.fathrpet.model.dto.TowerDTO;
import com.fathrpet.model.dto.TowerSlotDTO;
import com.fathrpet.model.entity.Tower;
import com.fathrpet.model.entity.TowerSlot;

import java.util.List;

public class TowerMapper {

    public TowerDTO toDTO(Tower tower){
        return TowerDTO.fromEntity(tower);
    }

    public TowerSlotDTO toDTO(TowerSlot slot){
        return TowerSlotDTO.fromEntity(slot);
    }

    public List<TowerSlotDTO> towerSlotDTOList(List<TowerSlot> slots){
        return slots.stream().map(this::toDTO).toList();
    }
}
