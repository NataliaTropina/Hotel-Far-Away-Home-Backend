package de.ait.todo.dto;

import de.ait.todo.models.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import de.ait.todo.models.Room;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    @Schema(description = "идентификатор комнаты", example = "1")
    private int id;

    @Schema(description = "Номер комнаты", example = "101")
    private String number;

    @Schema(description = "Тип комнаты", example = "Люкс")
    @Enumerated(value = EnumType.STRING)
    private Room.TypeOfRoom typeOfRoom;

    @Schema(description = "Цена комнаты", example = "550,00")
    private double price;

    public static RoomDto from (Room room){
        return builder()
                .id(room.getId())
                .number(room.getNumber())
                .typeOfRoom(room.getTypeOfRoom())
                .price(room.getPrice())
                .build();
    }

    public static List<RoomDto> from (List<Room> rooms){
        return rooms.stream()
                .map(RoomDto::from)
                .collect(Collectors.toList());
    }
}
