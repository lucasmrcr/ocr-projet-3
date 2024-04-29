package fr.chatop.api.controller;

import fr.chatop.api.dto.request.RegisterUserDTO;
import fr.chatop.api.models.User;
import fr.chatop.api.services.IJWTService;
import fr.chatop.api.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IJWTService jwtService;
    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(Authentication authentication) {
        return ResponseEntity.ok(jwtService.generateToken(authentication));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUser) {
        return ResponseEntity.ok(userService.register(registerUser));
    }

}
