package fr.chatop.api.services.impl;

import fr.chatop.api.dto.request.message.CreateMessageDTO;
import fr.chatop.api.models.Message;
import fr.chatop.api.models.Rental;
import fr.chatop.api.models.User;
import fr.chatop.api.services.IMessageService;
import fr.chatop.api.services.IRentalService;
import fr.chatop.api.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final IRentalService rentalService;
    private final IUserService userService;


    @Override
    public void sendMessage(CreateMessageDTO createMessage) {
        Rental rental = rentalService.getRental(createMessage.rentalId());
        User user = userService.getUser(createMessage.userId());
        Message message = new Message();
        message.setMessage(createMessage.message());
        message.setRental(rental);
        message.setUser(user);
    }
}
