package fr.chatop.api.services.impl;

import fr.chatop.api.dto.request.auth.LoginDTO;
import fr.chatop.api.dto.request.auth.RegisterUserDTO;
import fr.chatop.api.exception.ResponseEntityException;
import fr.chatop.api.models.User;
import fr.chatop.api.repositories.IUserRepository;
import fr.chatop.api.services.IJWTService;
import fr.chatop.api.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // When user is not found, we throw an exception which will be caught by exception handler
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "User %s not found", username));
    }

    @Override
    public String register(RegisterUserDTO registerUser) {
        User user = new User();
        user.setName(registerUser.name());
        user.setEmail(registerUser.email());
        user.setPassword(passwordEncoder.encode(registerUser.password()));
        user = userRepository.save(user);
        // TWhen user is registered, the response will contain a JWT token
        return jwtService.generateToken(new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities()
        ));
    }

    @Override
    public User getConnectedUser() {
        // To get connected user, we use the subject of the jwt token. It contains the email of the user
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String subject = jwt.getSubject();
        // When user is not found, we throw an exception which will be caught by exception handler
        return userRepository.findByEmail(subject).orElseThrow(() -> new ResponseEntityException(HttpStatus.UNAUTHORIZED, "User not found"));
    }

    @Override
    public User getUser(int id) {
        // When user is not found, we throw an exception which will be caught by exception handler
        return userRepository.findById(id).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "User %d not found", id));
    }

    @Override
    public String login(LoginDTO login) {
        // To make login action, first we find the user by email and then we check if the password is correct
        // If nothing matches we throw an exception which will be caught by exception handler
        User loggedUser = userRepository.findByEmail(login.email())
            .filter(user -> passwordEncoder.matches(login.password(), user.getPassword()))
            .orElseThrow(() -> new ResponseEntityException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        // And then we generate a JWT token
        return jwtService.generateToken(new UsernamePasswordAuthenticationToken(
            loggedUser, null, loggedUser.getAuthorities()
        ));
    }
}
