package fr.chatop.api.dto.response.rental;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.chatop.api.models.Rental;
import io.swagger.v3.oas.annotations.media.Schema;

public record RentalDTO(
    @Schema(description = "Rental id", example = "1")
    int id,
    @Schema(description = "Rental name", example = "Nice house")
    String name,
    @Schema(description = "Rental surface", example = "100")
    double surface,
    @Schema(description = "Rental price", example = "1000")
    double price,
    @Schema(description = "Rental picture", example = "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg")
    String picture,
    @Schema(description = "Rental description", example = "Nice house in the countryside")
    String description,
    @Schema(description = "Rental owner id", example = "1")
    @JsonProperty("owner_id")
    int ownerId,
    @Schema(description = "Rental creation date", example = "2024/04/29")
    @JsonProperty("created_at")
    String createdAt,
    @Schema(description = "Rental update date", example = "2024/04/29")
    @JsonProperty("updated_at")
    String updatedAt
) {

    public static RentalDTO from(Rental rental) {
        return new RentalDTO(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwner().getId(), rental.getCreatedAt().toString(), rental.getUpdatedAt().toString());
    }

}
