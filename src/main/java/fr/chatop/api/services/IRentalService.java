package fr.chatop.api.services;

import fr.chatop.api.dto.request.rental.CreateRentalDTO;
import fr.chatop.api.dto.request.rental.UpdateRentalDTO;
import fr.chatop.api.models.Rental;

import java.util.List;

public interface IRentalService {

    List<Rental> getRentals();

    Rental getRental(int id);

    void create(CreateRentalDTO createRental);

    void update(int id, UpdateRentalDTO updateRental);
}
