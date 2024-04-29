package fr.chatop.api.services.impl;

import fr.chatop.api.dto.request.RegisterUserDTO;
import fr.chatop.api.models.User;
import fr.chatop.api.repositories.IUserRepository;
import fr.chatop.api.services.IJWTService;
import fr.chatop.api.services.IUserService;
import lombok.RequiredArgsConstructor;
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
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public String register(RegisterUserDTO registerUser) {
        User user = new User();
        user.setName(registerUser.name());
        user.setEmail(registerUser.email());
        user.setPassword(passwordEncoder.encode(registerUser.password()));
        user = userRepository.save(user);
        return jwtService.generateToken(new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities()
        ));
    }

    @Override
    public User getConnectedUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String subject = jwt.getSubject();
        return userRepository.findByEmail(subject).orElseThrow();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow();
    }
}
