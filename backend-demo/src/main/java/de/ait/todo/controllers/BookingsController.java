package de.ait.todo.controllers;

import de.ait.todo.controllers.api.BookingsApi;
import de.ait.todo.dto.BookingDto;
import de.ait.todo.dto.BookingsPage;
import de.ait.todo.dto.NewBookingDto;
import de.ait.todo.security.details.AuthenticatedUser;
import de.ait.todo.services.BookingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingsController implements BookingsApi {

    private final BookingsService bookingsService;


    @Override
    @PreAuthorize("isAuthenticated()")
    public Long createBooking(NewBookingDto newBooking, AuthenticatedUser currentUser) {

        return bookingsService.createBooking(newBooking, currentUser);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookingsPage getAll() {
        return bookingsService.getAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookingDto getBookingById(Long id) {
        return bookingsService.getBookingById(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public BookingDto updateBooking(Long bookingId, NewBookingDto newBooking, AuthenticatedUser currentUser) {
        return bookingsService.updateBooking(bookingId, newBooking, currentUser);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public BookingDto deleteBooking(Long bookingId, AuthenticatedUser currentUser) {
        return bookingsService.deleteBooking(bookingId, currentUser);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<BookingDto> getUserBookings(AuthenticatedUser currentUser) {
        Long currentUserId = currentUser.getUser().getId();
        return bookingsService.getUserBookings(currentUserId);
    }
}


