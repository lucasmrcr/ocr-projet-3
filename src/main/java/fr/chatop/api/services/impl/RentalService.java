package fr.chatop.api.services.impl;

import fr.chatop.api.exception.ResponseEntityException;
import fr.chatop.api.models.Rental;
import fr.chatop.api.repositories.IRentalRepository;
import fr.chatop.api.services.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService implements IRentalService {

    private final IRentalRepository rentalRepository;


    @Override
    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRental(int id) {
        return rentalRepository.findById(id).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Rental with id %d not found", id));
    }
}
