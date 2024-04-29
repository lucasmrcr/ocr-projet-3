package fr.chatop.api.services;

import org.springframework.security.core.Authentication;

public interface IJWTService {
    String generateToken(Authentication authentication);
}
