package de.ait.todo.dto;

import de.ait.todo.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Зарегистрированный пользователь")
public class UserDto {
    @Schema(description = "идентификатор пользователя", example = "1")
    private Long id;
    private String firstName;
    private String lastName;
    @Schema(description = "имя пользователя", example = "username")
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String phone;
    private List<Long> eventIds;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
