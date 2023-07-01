package de.ait.todo.services.impl;

import de.ait.todo.dto.NewRoomDto;
import de.ait.todo.dto.RoomDto;
import de.ait.todo.dto.RoomsPage;
import de.ait.todo.exceptions.NotFoundException;
import de.ait.todo.models.Room;
import de.ait.todo.repositories.RoomsRepository;
import de.ait.todo.services.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.todo.dto.UserDto.from;


@RequiredArgsConstructor
@Service
public class RoomsServiceImpl implements RoomsService {


    private final RoomsRepository roomsRepository;

    @Override
    public int createRoom(NewRoomDto newRoom) {

        Room room = Room.builder()
                .typeOfRoom(newRoom.getTypeOfRoom())
                .number(newRoom.getNumber())
                .price(newRoom.getPrice())
                .build();
        roomsRepository.save(room);

        return room.getId();
    }

    @Override
    public RoomsPage getAll() {

        List<Room> rooms = roomsRepository.findAll();

        return RoomsPage.builder()
                .data(RoomDto.from(rooms))
                .build();
    }

    @Override
    public RoomDto getRoomById(int id) {

        Room room = roomsRepository.findById(id)
                .orElseThrow( ()->
                        new NotFoundException("room with id <" + id + "> not found")

                );

        return RoomDto.from(room);
    }

    @Override
    public RoomDto updateRoom(int id, NewRoomDto newRoom) {

        Room room = roomsRepository.findById(id)
                .orElseThrow( ()->
                        new NotFoundException("room with id <" + id + "> not found")
                );
        room.setNumber(newRoom.getNumber());
        room.setTypeOfRoom(newRoom.getTypeOfRoom());
        room.setPrice(newRoom.getPrice());

        roomsRepository.save(room);

        return RoomDto.from(room);
    }

    public RoomDto deleteRoom (int id){

       Room room = roomsRepository.findById(id)
                       .orElseThrow( ()->
                               new NotFoundException("room with id <" + id + "> not found")
                       );

        roomsRepository.deleteById(id);

        return RoomDto.from(room);
    }

}
