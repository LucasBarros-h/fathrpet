package com.fathrpet.service;

import com.fathrpet.mappers.TowerMapper;
import com.fathrpet.model.dto.LeavePokemonRequestDTO;
import com.fathrpet.model.dto.PokemonDTO;
import com.fathrpet.model.dto.TowerSlotDTO;
import com.fathrpet.model.dto.WithdrawPokemonResponseDTO;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.Tower;
import com.fathrpet.model.entity.TowerSlot;
import com.fathrpet.model.entity.User;
import com.fathrpet.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TowerService {
    private final TowerRepository towerRepository;
    private final TowerSlotRepository towerSlotRepository;
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;
    private final WalletService walletService;
    private final TowerMapper towerMapper;

    @Transactional
    public TowerSlotDTO leavePokemonInTower(LeavePokemonRequestDTO request){
        Tower tower = towerRepository.findById(request.towerId()).orElseThrow(() -> new RuntimeException("Torre não encontrada"));

        Pokemon pokemon = pokemonRepository.findById(request.pokemonId()).orElseThrow(() -> new RuntimeException("Pokemon não encontrado"));

        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(pokemon.isListed() || pokemon.getOwner() == null || !pokemon.getOwner().equals(user)){
            throw new RuntimeException("Erro ao deixar o pokemon na torre");
        }

        //TODO implementar a mudança para que o mesmo pokemon possa voltar a torre

        TowerSlot slot = new TowerSlot();
        slot.setTower(tower);
        slot.setPokemon(pokemon);
        slot.setUser(user);
        slot.setEntryTime(LocalDateTime.now());
        slot.setMinimumExitTime(LocalDateTime.now().plusMinutes(request.minutes()));
        slot.setWithdrawn(false);

        user.getInventory().remove(pokemon);
        userRepository.save(user);

        return towerMapper.toDTO(towerSlotRepository.save(slot));
    }

    @Transactional
    public WithdrawPokemonResponseDTO withdrawPokemonFromTower(Long slotId, Long userId){
        TowerSlot slot = towerSlotRepository.findWithPokemonAndUserById(slotId).orElseThrow(() -> new RuntimeException("Espaço na torre não encontrado"));

        if(!slot.getUser().getId().equals(userId)){
            throw new RuntimeException("Esse espaço não pertence ao usuário");
        }

        if(slot.isWithdrawn()){
            throw new RuntimeException("O pokemon já foi retirado");
        }

        if(LocalDateTime.now().isBefore(slot.getMinimumExitTime())){
            throw new RuntimeException("Tempo mínimo não foi cumprido");
        }

        User user = slot.getUser();
        Pokemon pokemon = slot.getPokemon();
        walletService.addFunds(userId, 5.0);
        user.getInventory().add(pokemon);
        slot.setWithdrawn(true);

        userRepository.save(user);
        towerSlotRepository.save(slot);

        return new WithdrawPokemonResponseDTO("Pokemon retirado com sucesso", PokemonDTO.fromEntity(pokemon), 5.0);
    }

    public List<TowerSlotDTO> getUserActiveSlots(Long userId){
        return towerMapper.towerSlotDTOList(towerSlotRepository.findByUser_IdAndWithdrawnFalse(userId));
    }
}
