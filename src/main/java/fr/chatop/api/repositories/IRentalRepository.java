package fr.chatop.api.repositories;

import fr.chatop.api.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentalRepository extends JpaRepository<Rental, Integer> {
}
