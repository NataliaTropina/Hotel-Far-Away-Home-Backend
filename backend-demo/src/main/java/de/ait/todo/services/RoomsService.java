package de.ait.todo.services;

import de.ait.todo.dto.NewRoomDto;
import de.ait.todo.dto.RoomDto;
import de.ait.todo.dto.RoomsPage;
import de.ait.todo.models.Room;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface RoomsService {

    int createRoom (NewRoomDto newRoom);

    RoomsPage getAll ();

    RoomDto getRoomById (int id);

    RoomDto updateRoom (int id, NewRoomDto newRoom);

    RoomDto deleteRoom(int id);

    List<RoomDto> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);

    List<RoomDto> priceFilter(double startPrice, double endPrice);

    List<RoomDto> typeOfRoomFilter(Room.TypeOfRoom typeOfRoom);
}
