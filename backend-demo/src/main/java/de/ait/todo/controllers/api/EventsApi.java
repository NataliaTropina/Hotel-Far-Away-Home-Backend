package de.ait.todo.controllers.api;

import de.ait.todo.dto.*;
import de.ait.todo.security.details.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = {
        @Tag(name = "Events")
})
@RequestMapping("/api/events")
public interface EventsApi {

     @Operation(summary = "Создание нового события пользователем" , description = "Доступно только зарегистрированным пользователю")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "201", description = "Новое событие",
                     content = {
                             @Content(mediaType = "application/json",
                                     schema = @Schema(implementation = EventDto.class))
                     }
             )
     })

     @PostMapping(value = "")
     Long createEvent (@RequestBody NewEventDto newEvent);


    @Operation(summary = "Получение списка всех событий", description = "Доступно всем ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с событиями",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EventsPage.class))
                    }
            )
    })
     @GetMapping(value = "")
    EventsPage getAll ();

    @Operation(summary = "Получение событий и по текущему пользователю", description = "Доступно только зарегистрированному пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получение событий и по текущему пользователю",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })

    @GetMapping("/by-user")
    List<EventDto> getUserEvents (@Parameter(hidden = true)
                                  @AuthenticationPrincipal AuthenticatedUser currentUser);
    @Operation(summary = "Удаление событий по ID", description = "доступно зарегистрированному пользователю и администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаление событий по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })

    @DeleteMapping("/{id}")
    ResponseEntity<EventDto> deleteEventById (@PathVariable("id")  Long eventId,
                                              @Parameter(hidden = true)
                                              @AuthenticationPrincipal AuthenticatedUser currentUser);

    @Operation(summary = "Обновление событий по ID", description = "доступно только зарегистрированному пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление событий по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookingDto.class))
                    }
            )
    })

    @PutMapping("/{id}")
    ResponseEntity<EventDto> updateEventById (@Parameter(hidden = true)
                                              @AuthenticationPrincipal AuthenticatedUser currentUser,
                                              @PathVariable("id") Long eventId,
                                              @RequestBody NewEventDto newEvent);

}
