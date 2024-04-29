package fr.chatop.api.services;

import fr.chatop.api.dto.request.RegisterUserDTO;
import fr.chatop.api.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    String register(RegisterUserDTO registerUser);

    User getConnectedUser();
}
