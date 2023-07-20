package de.ait.todo.services;

import de.ait.todo.dto.BookingDto;
import de.ait.todo.dto.BookingsPage;
import de.ait.todo.dto.NewBookingDto;
import de.ait.todo.security.details.AuthenticatedUser;

import java.util.List;

public interface BookingsService {

    Long createBooking(NewBookingDto newBooking, AuthenticatedUser currentUser);

    BookingsPage getAll();

    BookingDto getBookingById(Long id);

    BookingDto updateBooking (Long bookingId, NewBookingDto newBooking, AuthenticatedUser currentUser);

    BookingDto deleteBooking (Long id, AuthenticatedUser currentUser);

    List<BookingDto> getUserBookings (Long currentUserId);
}
