package de.ait.todo.controllers.api;

import de.ait.todo.dto.BookingDto;
import de.ait.todo.dto.BookingsPage;
import de.ait.todo.dto.NewBookingDto;
import de.ait.todo.dto.RoomsPage;
import de.ait.todo.models.Booking;
import de.ait.todo.security.details.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = {
        @Tag(name = "Bookings")
})
@RequestMapping("/api/bookings")
public interface BookingsApi {

    @Operation(summary = "Создание нового бронирования")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Новое бронирование",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })

    @PostMapping(value = "")
    Long createBooking(@RequestBody NewBookingDto newBooking,
                       @Parameter(hidden = true)
                       @AuthenticationPrincipal AuthenticatedUser currentUser);

    @Operation(summary = "Получение списка всех бронирований ", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с бронированиями",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingsPage.class))
                    }
            )
    })
    @GetMapping(value = "")
    BookingsPage getAll ();

    @Operation(summary = "Получение бронирования по ID", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирование по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })

    @GetMapping(value = "/{id}")
    BookingDto getBookingById (@PathVariable("id") Long id);


    @Operation(summary = "Обновление бронирования по ID", description = "доступно зарегистрированному пользователю и администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление бронирования по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })
    @PutMapping(value = "/{id}")
    BookingDto updateBooking (@PathVariable("id") Long bookingId,
                              @RequestBody NewBookingDto newBooking,
                              @Parameter(hidden = true)
                              @AuthenticationPrincipal AuthenticatedUser currentUser);

    @Operation(summary = "Удаление бронирования по ID ", description = "доступно зарегистрированному пользователю и администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаление бронирования по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })

    @DeleteMapping(value = "/{id}")
    BookingDto deleteBooking (@PathVariable("id") Long bookingId,
                              @Parameter(hidden = true)
                              @AuthenticationPrincipal AuthenticatedUser currentUser);


    @Operation(summary = "Получение бронирований и по текущему пользователю", description = "Доступно только зарегистрированному пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получение бронирований и по текущему пользователю",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })

    @GetMapping(value = "/by-user")
    List<BookingDto> getUserBookings (@Parameter(hidden = true)
                                      @AuthenticationPrincipal AuthenticatedUser currentUser);
}
