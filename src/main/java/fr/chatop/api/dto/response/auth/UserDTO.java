package fr.chatop.api.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.chatop.api.models.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record UserDTO(
    @Schema(description = "User id")
    int id,
    @Schema(description = "User name")
    String name,
    @Schema(description = "User email")
    String email,
    @Schema(description = "User creation date")
    @JsonProperty("created_at")
    String createdAt,
    @Schema(description = "User update date")
    @JsonProperty("updated_at")
    String updatedAt
) {
    private static final String PATTERN_FORMAT = "yyyy/MM/dd";
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());


    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), formatter.format(user.getCreatedAt()), formatter.format(user.getUpdatedAt()));
    }

}
