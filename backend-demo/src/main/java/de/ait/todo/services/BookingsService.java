package de.ait.todo.services;

import de.ait.todo.dto.BookingDto;
import de.ait.todo.dto.BookingsPage;
import de.ait.todo.dto.NewBookingDto;

import java.util.List;

public interface BookingsService {

    Long createBooking(NewBookingDto newBooking);

    BookingsPage getAll();

    BookingDto getBookingById(Long id);

    BookingDto updateBooking (Long bookingId, NewBookingDto newBooking);

    BookingDto deleteBooking (Long id);

    List<BookingDto> getUserBookings (Long currentUserId);
}
