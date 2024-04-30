package fr.chatop.api.dto.response.rental;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ListRentalDTO(
    @Schema(description = "List of rentals")
    List<RentalDTO> rentals
) {
}
