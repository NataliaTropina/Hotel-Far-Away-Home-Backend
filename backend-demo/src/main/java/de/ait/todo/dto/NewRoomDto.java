package de.ait.todo.dto;

import de.ait.todo.models.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRoomDto {

    private String number;
    @Enumerated(value = EnumType.STRING)
    private Room.TypeOfRoom typeOfRoom;
    private double price;
}
