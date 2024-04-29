package fr.chatop.api.dto.request;

public record RegisterUserDTO(
    String email,
    String name,
    String password
) {
}
