package fr.chatop.api.dto.request.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateMessageDTO(
    String message,
    @JsonProperty("user_id")
    int userId,
    @JsonProperty("rental_id")
    int rentalId
) {
}
