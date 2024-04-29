package fr.chatop.api.dto.response.exception;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseExceptionDTO(
    @Schema(description = "Error message", example = "User not found")
    String error
) {
}
