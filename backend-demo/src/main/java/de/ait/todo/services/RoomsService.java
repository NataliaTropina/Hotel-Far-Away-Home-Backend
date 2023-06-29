package de.ait.todo.services;

import de.ait.todo.dto.NewRoomDto;
import de.ait.todo.dto.RoomDto;
import de.ait.todo.dto.RoomsPage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomsService {

    int createRoom (NewRoomDto newRoom);

    RoomsPage getAll ();

    RoomDto getRoomById (int id);

    RoomDto updateRoom (int id, NewRoomDto newRoom);

    RoomDto deleteRoom(int id);
}
