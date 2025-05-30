package com.fathrpet.repositories;

import com.fathrpet.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"inventory"})
    Optional<User> findWithInventoryById(Long userId);

}
