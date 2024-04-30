package fr.chatop.api.dto.request.auth;

public record LoginDTO (
    String email,
    String password
){
}
