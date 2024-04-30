package fr.chatop.api.controller;

import fr.chatop.api.dto.request.rental.CreateRentalDTO;
import fr.chatop.api.dto.request.rental.UpdateRentalDTO;
import fr.chatop.api.dto.response.MessageDTO;
import fr.chatop.api.dto.response.exception.ResponseExceptionDTO;
import fr.chatop.api.dto.response.rental.ListRentalDTO;
import fr.chatop.api.dto.response.rental.RentalDTO;
import fr.chatop.api.services.IRentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ListRentalDTO> getRentals() {
        return ResponseEntity.ok(new ListRentalDTO(rentalService.getRentals().stream().map(RentalDTO::from).toList()));
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
    public ResponseEntity<RentalDTO> getRental(@PathVariable() int id) {
        return ResponseEntity.ok(RentalDTO.from(rentalService.getRental(id)));
    }

    @Operation(
        summary = "Create rental",
        description = "Create a rental"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental created", content = @Content(schema = @Schema(implementation = MessageDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<MessageDTO> create(@ModelAttribute CreateRentalDTO createRental) {
        rentalService.create(createRental);
        return ResponseEntity.ok(new MessageDTO("Rental created !"));
    }

    @Operation(
        summary = "Update rental",
        description = "Update a rental"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rental updated", content = @Content(schema = @Schema(implementation = MessageDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable() int id, @RequestBody UpdateRentalDTO updateRental) {
        rentalService.update(id, updateRental);
        return ResponseEntity.ok(new MessageDTO("Rental updated !"));
    }

}
