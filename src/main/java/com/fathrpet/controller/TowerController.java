package com.fathrpet.controller;

import com.fathrpet.model.dto.LeavePokemonRequestDTO;
import com.fathrpet.model.dto.TowerSlotDTO;
import com.fathrpet.model.dto.WithdrawPokemonResponseDTO;
import com.fathrpet.service.TowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/towers/v1")
@Validated
@RequiredArgsConstructor
public class TowerController {

    private final TowerService towerService;

    @PostMapping("/leave-pokemon")
    public ResponseEntity<TowerSlotDTO> leavePokemon(@Valid @RequestBody LeavePokemonRequestDTO request){
        TowerSlotDTO slot = towerService.leavePokemonInTower(request);

        return ResponseEntity.ok(slot);
    }

    @PostMapping("/slots/{slotId}/withdraw")
    public ResponseEntity<WithdrawPokemonResponseDTO> withdrawPokemon(@PathVariable Long slotId, @RequestParam Long userId){
        WithdrawPokemonResponseDTO response = towerService.withdrawPokemonFromTower(slotId, userId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}/active-slots")
    public ResponseEntity<List<TowerSlotDTO>> getUserActiveSlots(@PathVariable Long userId){
        List<TowerSlotDTO> slots = towerService.getUserActiveSlots(userId);
        return ResponseEntity.ok(slots);
    }
}
