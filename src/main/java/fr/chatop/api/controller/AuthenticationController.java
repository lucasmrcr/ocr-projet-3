package fr.chatop.api.controller;

import fr.chatop.api.services.IJWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IJWTService jwtService;

    @PostMapping("/login")
    public String login(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

}
