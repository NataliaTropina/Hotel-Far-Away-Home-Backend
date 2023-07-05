package de.ait.todo.services.impl;

import de.ait.todo.dto.BookingDto;
import de.ait.todo.dto.BookingsPage;
import de.ait.todo.dto.NewBookingDto;
import de.ait.todo.dto.UserDto;
import de.ait.todo.exceptions.NotFoundException;
import de.ait.todo.models.Booking;
import de.ait.todo.models.Room;
import de.ait.todo.models.User;
import de.ait.todo.repositories.BookingsRepository;
import de.ait.todo.repositories.RoomsRepository;
import de.ait.todo.repositories.UsersRepository;
import de.ait.todo.services.BookingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;

    private final UsersRepository userRepository;

    private final RoomsRepository roomsRepository;

    @Override
    public Long createBooking(NewBookingDto newBooking) {


        List<Room> roomList = roomsRepository.findAllById(newBooking.getRoomIds());
        Long userId = newBooking.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow( ()->
                        new NotFoundException("user with id <" + userId + "> not found")
                );

        for(int i = 0; i < roomList.size(); i++){
                roomList.get(i).setBooked(true);
            }

        Booking booking = Booking.builder()
                .cheCkIn(newBooking.getCheCkIn())
                .checkOut(newBooking.getCheckOut())
                .rooms(roomList)
                .user(user)
                .build();

        return bookingsRepository.save(booking).getId();
    }

    public BookingsPage getAll(){
        List<Booking> bookings = bookingsRepository.findAll();

        return BookingsPage.builder()
                .data(BookingDto.from(bookings))
                .build();
    }

    public BookingDto getBookingById(Long id){
        Booking booking = bookingsRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Booking with id <" + id + "> not found")

                );
        return BookingDto.from(booking);
    }

    public BookingDto updateBooking (Long bookingId, NewBookingDto newBooking){
        Booking booking = bookingsRepository.findById(bookingId)
                .orElseThrow(()->
                        new NotFoundException("Booking with id <" + bookingId + "> not found")
                );
        booking.setCheCkIn(newBooking.getCheCkIn());
        booking.setCheckOut(newBooking.getCheckOut());

        List<Room> roomsList = roomsRepository.findAllById(newBooking.getRoomIds());

        booking.setRooms(roomsList);
        bookingsRepository.save(booking);

        return BookingDto.from(booking);
    }

    @Transactional
    @Override
    public BookingDto deleteBooking(Long id) {

        Booking booking = bookingsRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Booking with id <" + id + "> not found")
                        );
        bookingsRepository.deleteById(id);

        List<Room> roomList = booking.getRooms();

        for(int i = 0; i < roomList.size(); i++){
            roomList.get(i).setBooked(false);
        }

        roomsRepository.saveAll(roomList);

        return BookingDto.from(booking);
    }

    @Override
    public List<BookingDto> getUserBookings(Long currentUserId) {

        User user = userRepository.findById(currentUserId)
                .orElseThrow(()->
                        new NotFoundException("User with id <" + currentUserId + "> not found"));

        List<Booking> bookingsByUser = bookingsRepository.findAllByUser(user);

        return BookingDto.from(bookingsByUser);
    }
}

