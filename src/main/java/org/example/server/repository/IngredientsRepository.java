package org.example.server.repository;

import org.example.server.model.potions.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
}
