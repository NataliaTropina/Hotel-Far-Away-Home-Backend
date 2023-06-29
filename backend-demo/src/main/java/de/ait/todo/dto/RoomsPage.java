package de.ait.todo.dto;

import de.ait.todo.models.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Страница с номерами гостиницы")
public class RoomsPage {

    @Schema(description = "Номера гостиницы")
    private List<RoomDto> data;
}
