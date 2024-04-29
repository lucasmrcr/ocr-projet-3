package fr.chatop.api.dto.response.auth;

import fr.chatop.api.models.User;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record UserDTO(
    int id,
    String name,
    String email,
    String createdAt,
    String updatedAt
) {
    private static final String PATTERN_FORMAT = "yyyy/MM/dd";
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());


    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), formatter.format(user.getCreatedAt()), formatter.format(user.getUpdatedAt()));
    }

}
