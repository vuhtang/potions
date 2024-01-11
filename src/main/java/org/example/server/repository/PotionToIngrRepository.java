package org.example.server.repository;

import org.example.server.model.potions.Potion;
import org.example.server.model.potions.PotionToIngr;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PotionToIngrRepository extends JpaRepository<PotionToIngr, Integer> {

    List<PotionToIngr> findAllByPotion(Potion potion);
}
