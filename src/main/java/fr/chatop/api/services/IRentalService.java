package fr.chatop.api.services;

import fr.chatop.api.models.Rental;

import java.util.List;

public interface IRentalService {

    List<Rental> getRentals();

    Rental getRental(int id);
}
