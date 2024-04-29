package fr.chatop.api.controller;

import fr.chatop.api.dto.request.message.CreateMessageDTO;
import fr.chatop.api.dto.response.MessageDTO;
import fr.chatop.api.services.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final IMessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(@RequestBody CreateMessageDTO createMessage) {
        messageService.sendMessage(createMessage);
        return ResponseEntity.ok(new MessageDTO("Message send with success"));
    }

}
