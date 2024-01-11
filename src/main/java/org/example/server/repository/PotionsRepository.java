package org.example.server.repository;

import org.example.server.model.potions.Potion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotionsRepository extends JpaRepository<Potion, Integer> {
}
