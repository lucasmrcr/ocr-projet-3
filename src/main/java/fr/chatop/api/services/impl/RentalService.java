package fr.chatop.api.services.impl;

import fr.chatop.api.dto.request.rental.CreateRentalDTO;
import fr.chatop.api.dto.request.rental.UpdateRentalDTO;
import fr.chatop.api.exception.ResponseEntityException;
import fr.chatop.api.models.Rental;
import fr.chatop.api.models.User;
import fr.chatop.api.repositories.IRentalRepository;
import fr.chatop.api.services.IPictureService;
import fr.chatop.api.services.IRentalService;
import fr.chatop.api.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService implements IRentalService {

    private final IRentalRepository rentalRepository;
    private final IPictureService pictureService;
    private final IUserService userService;

    @Override
    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRental(int id) {
        // When rental is not found, we throw an exception which will be caught by exception handler
        return rentalRepository.findById(id).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Rental with id %d not found", id));
    }

    @Override
    public void create(CreateRentalDTO createRental) {
        String pictureUrl = pictureService.savePicture(createRental.picture());
        User user = userService.getConnectedUser();

        Rental rental = new Rental();
        rental.setName(createRental.name());
        rental.setSurface(createRental.surface());
        rental.setPrice(createRental.price());
        rental.setDescription(createRental.description());
        rental.setPicture(pictureUrl);
        rental.setOwner(user);
        rentalRepository.save(rental);
    }

    @Override
    public void update(int id, UpdateRentalDTO updateRental) {
        Rental rental = getRental(id);
        if (updateRental.name() != null)
            rental.setName(updateRental.name());
        if (updateRental.surface() != null)
            rental.setSurface(updateRental.surface());
        if (updateRental.price() != null)
            rental.setPrice(updateRental.price());
        if (updateRental.description() != null)
            rental.setDescription(updateRental.description());
        rentalRepository.save(rental);
    }
}
