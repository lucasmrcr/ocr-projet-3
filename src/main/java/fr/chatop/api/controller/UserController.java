package fr.chatop.api.controller;

import fr.chatop.api.dto.response.auth.UserDTO;
import fr.chatop.api.dto.response.exception.ResponseExceptionDTO;
import fr.chatop.api.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @Operation(
        summary = "Get user",
        description = "Retrieve user by id"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ResponseExceptionDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable() int id) {
        return ResponseEntity.ok(UserDTO.from(userService.getUser(id)));
    }

}
