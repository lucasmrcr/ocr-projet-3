package fr.chatop.api.controller;

import fr.chatop.api.dto.request.message.CreateMessageDTO;
import fr.chatop.api.dto.response.MessageDTO;
import fr.chatop.api.services.IMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
        summary = "Send message",
        description = "Send a message"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Message send with success", content = @Content(schema = @Schema(implementation = MessageDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(@RequestBody CreateMessageDTO createMessage) {
        messageService.sendMessage(createMessage);
        return ResponseEntity.ok(new MessageDTO("Message send with success"));
    }

}
