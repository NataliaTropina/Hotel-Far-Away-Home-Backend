package de.ait.todo.controllers;

import de.ait.todo.controllers.api.BookingsApi;
import de.ait.todo.dto.BookingDto;
import de.ait.todo.dto.BookingsPage;
import de.ait.todo.dto.NewBookingDto;
import de.ait.todo.services.BookingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingsController implements BookingsApi {

    private final BookingsService bookingsService;


    @Override
    @PreAuthorize("isAuthenticated()")
    public Long createBooking(NewBookingDto newBooking) {
        return bookingsService.createBooking(newBooking);
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
    public BookingDto updateBooking(Long bookingId, NewBookingDto newBooking) {
        return bookingsService.updateBooking(bookingId, newBooking);
    }

    @Override
    public BookingDto deleteBooking(Long bookingId) {
        return bookingsService.deleteBooking(bookingId);
    }
}
