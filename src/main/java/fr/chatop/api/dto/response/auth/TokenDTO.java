package fr.chatop.api.dto.response.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenDTO(
    @Schema(description = "Token used to authenticate user")
    String token
) {
}
