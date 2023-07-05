package de.ait.todo.services.impl;

import de.ait.todo.dto.NewRoomDto;
import de.ait.todo.dto.RoomDto;
import de.ait.todo.dto.RoomsPage;
import de.ait.todo.exceptions.IncorrectDeleteException;
import de.ait.todo.exceptions.NotFoundException;
import de.ait.todo.models.Booking;
import de.ait.todo.models.Room;
import de.ait.todo.repositories.BookingsRepository;
import de.ait.todo.repositories.RoomsRepository;
import de.ait.todo.services.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static de.ait.todo.dto.UserDto.from;


@RequiredArgsConstructor
@Service
public class RoomsServiceImpl implements RoomsService {


    private final RoomsRepository roomsRepository;

    private final BookingsRepository bookingsRepository;

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
       if(room.isBooked()){
           throw new IncorrectDeleteException("Cannot delete a booked room");
       }

        roomsRepository.deleteById(id);

        return RoomDto.from(room);
    }

    @Override
    public List<RoomDto> getAvailableRooms(LocalDate startDate, LocalDate endDate) {

        List<Room> allRooms = roomsRepository.findAll();

        List<Room> availableRooms = new ArrayList<>();

        for (Room room : allRooms) {
            boolean isAvailable = true;
            List<Booking> allBookings = bookingsRepository.findAllByRoomsContains(room);

            // Проверка наличия брони для комнаты в заданный период
            for (Booking currentBooking : allBookings) {
                if (((currentBooking.getCheCkIn().isEqual(startDate) || currentBooking.getCheCkIn().isAfter(startDate)) && (currentBooking.getCheCkIn().isEqual(endDate) || currentBooking.getCheCkIn().isBefore(endDate))) ||
                        ((currentBooking.getCheckOut().isEqual(startDate) || currentBooking.getCheckOut().isAfter(startDate)) && (currentBooking.getCheckOut().isEqual(endDate) || currentBooking.getCheckOut().isBefore(endDate)))) {
                    // Промежуток дат пересекается с startBookingDate

                    isAvailable = false;
                    System.out.println("Промежуток дат пересекается");
                    break;
                } else {
                    // Промежуток дат не пересекается с startBookingDate
                    System.out.println("Промежуток дат не пересекается");
                }
            }

            if (isAvailable) {
                availableRooms.add(room);
            }
        }
        return RoomDto.from(availableRooms);
    }
}
