package fr.chatop.api.controller;

import fr.chatop.api.dto.request.auth.LoginDTO;
import fr.chatop.api.dto.request.auth.RegisterUserDTO;
import fr.chatop.api.dto.response.auth.TokenDTO;
import fr.chatop.api.dto.response.auth.UserDTO;
import fr.chatop.api.services.IJWTService;
import fr.chatop.api.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IJWTService jwtService;
    private final IUserService userService;

    @Operation(
        summary = "Login",
        description = "Login with email and password"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO login) {
        return ResponseEntity.ok(new TokenDTO(userService.login(login)));
    }

    @Operation(
        summary = "Register",
        description = "Register with email, password and name"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Registered", content = @Content(schema = @Schema(implementation = TokenDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody RegisterUserDTO registerUser) {
        return ResponseEntity.ok(new TokenDTO(userService.register(registerUser)));
    }

    @Operation(
        summary = "Get myself",
        description = "Retrieve id, name, email, created_at and updated_at"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        return ResponseEntity.ok(UserDTO.from(userService.getConnectedUser()));
    }

}
