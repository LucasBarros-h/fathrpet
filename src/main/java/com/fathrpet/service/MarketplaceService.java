package com.fathrpet.service;

import com.fathrpet.exception.ResourceNotFoundException;
import com.fathrpet.model.entity.Marketplace;
import com.fathrpet.model.entity.Pokemon;
import com.fathrpet.model.entity.User;
import com.fathrpet.repositories.MarketplaceRepository;
import com.fathrpet.repositories.PokemonRepository;
import com.fathrpet.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketplaceService {

    private final PokemonRepository pokemonRepository;
    private final WalletService walletService;
    private final MarketplaceRepository marketplaceRepository;
    private final UserRepository userRepository;

    public Marketplace findListingById(Long marketplaceId){
        return marketplaceRepository.findById(marketplaceId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public List<Marketplace> findAllListings(){
        return marketplaceRepository.findAll();
    }


    @Transactional
    public Pokemon removeFromListing(Long listingId) {

            Marketplace listing = marketplaceRepository.findWithPokemonAndSellerById(listingId).orElseThrow(() -> new ResourceNotFoundException("Orderm de venda não encontrada"));

            if(listing.isSold()){
                throw new ResourceNotFoundException("Não é possível remover uma ordem de venda já removida ou inexistente");
            }

            Pokemon pokemon = listing.getPokemon();
            User seller = listing.getSeller();

            if(!pokemon.getOwner().equals(seller)){
                throw new DataIntegrityViolationException("Inconsistência na propriedade do pokemon");
            }

            seller.getAllListings().removeIf(id -> id.getId().equals(listingId));

            pokemon.setListed(false);
            pokemonRepository.save(pokemon);

            if(!seller.getInventory().contains(pokemon)){
                seller.getInventory().add(pokemon);
            }

            userRepository.save(seller);
            marketplaceRepository.deleteByIdCustom(listingId);

            marketplaceRepository.flush();

            return pokemon;

    }

    @Transactional
    public Pokemon buyPokemon(Long listingId, Long buyerId){
        Marketplace listing = marketplaceRepository.findById(listingId).orElseThrow(() -> new ResourceNotFoundException("Ordem de venda não encontrada"));

        User buyer = userRepository.findById(buyerId).orElseThrow(() -> new ResourceNotFoundException("Comprador não encontrado"));

        walletService.transferFunds(buyerId, listing.getSeller().getId(), listing.getPrice());

        Pokemon boughtPokemon = listing.getPokemon();
        boughtPokemon.setOwner(buyer);
        buyer.getInventory().add(boughtPokemon);

        listing.setSold(true);

        marketplaceRepository.save(listing);
        userRepository.save(buyer);

        return pokemonRepository.save(boughtPokemon);
    }

}
