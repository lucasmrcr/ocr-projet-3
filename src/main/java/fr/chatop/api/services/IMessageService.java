package fr.chatop.api.services;

import fr.chatop.api.dto.request.message.CreateMessageDTO;

public interface IMessageService {
    void sendMessage(CreateMessageDTO createMessage);
}
