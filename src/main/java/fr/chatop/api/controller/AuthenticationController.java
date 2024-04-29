package fr.chatop.api.controller;

import fr.chatop.api.dto.request.RegisterUserDTO;
import fr.chatop.api.dto.response.auth.TokenDTO;
import fr.chatop.api.dto.response.auth.UserDTO;
import fr.chatop.api.models.User;
import fr.chatop.api.services.IJWTService;
import fr.chatop.api.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IJWTService jwtService;
    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(Authentication authentication) {
        return ResponseEntity.ok(new TokenDTO(jwtService.generateToken(authentication)));
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody RegisterUserDTO registerUser) {
        return ResponseEntity.ok(new TokenDTO(userService.register(registerUser)));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        return ResponseEntity.ok(UserDTO.from(userService.getConnectedUser()));
    }

}
