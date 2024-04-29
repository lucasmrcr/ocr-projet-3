package fr.chatop.api.dto.request.rental;

public record UpdateRentalDTO(
    String name,
    Double price,
    Double surface,
    String description
) {
}
