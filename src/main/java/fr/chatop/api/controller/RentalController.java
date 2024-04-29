package fr.chatop.api.controller;

import fr.chatop.api.dto.response.auth.UserDTO;
import fr.chatop.api.dto.response.exception.ResponseExceptionDTO;
import fr.chatop.api.dto.response.rental.RentalDTO;
import fr.chatop.api.services.IRentalService;
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

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final IRentalService rentalService;

    @Operation(
        summary = "Get rentals",
        description = "Retrieve all rentals"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RentalDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<RentalDTO>> getRentals() {
        return ResponseEntity.ok(rentalService.getRentals().stream().map(RentalDTO::from).toList());
    }

    @Operation(
        summary = "Get rental",
        description = "Retrieve rental by id"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RentalDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ResponseExceptionDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRental(@PathVariable() int id){
        return ResponseEntity.ok(RentalDTO.from(rentalService.getRental(id)));
    }

}
