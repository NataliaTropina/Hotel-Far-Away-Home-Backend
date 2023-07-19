package de.ait.todo.controllers;

import de.ait.todo.controllers.api.RoomsApi;
import de.ait.todo.dto.NewRoomDto;
import de.ait.todo.dto.RoomDto;
import de.ait.todo.dto.RoomsPage;
import de.ait.todo.models.Room;
import de.ait.todo.services.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoomsController implements RoomsApi {

    private final RoomsService roomsService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public int createRoom(NewRoomDto newRoom) {
        return roomsService.createRoom(newRoom);
    }

    @Override
    public RoomsPage getAll() {
        return roomsService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<RoomDto> getRoomById(int id) {
        return ResponseEntity.ok(roomsService.getRoomById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<RoomDto> updateRoom(int id, NewRoomDto newRoom) {

        return ResponseEntity.ok(roomsService.updateRoom(id, newRoom));
    }

    @Override
    public ResponseEntity<RoomDto> deleteRoom(int id) {
        return ResponseEntity.ok(roomsService.deleteRoom(id));
    }

    @Override
    public List<RoomDto> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return roomsService.getAvailableRooms(checkInDate, checkOutDate);
    }

    @Override
    public List<RoomDto> priceFilter(double startPrice, double endPrice) {
        return roomsService.priceFilter(startPrice, endPrice);
    }

    @Override
    public List<RoomDto> typeOfRoomFilter(Room.TypeOfRoom typeOfRoom) {
        return roomsService.typeOfRoomFilter(typeOfRoom);
    }
}
