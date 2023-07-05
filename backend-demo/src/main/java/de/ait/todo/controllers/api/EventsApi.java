package de.ait.todo.controllers.api;

import de.ait.todo.dto.NewEventDto;
import de.ait.todo.dto.RoomDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                                     schema = @Schema(implementation = RoomDto.class))
                     }
             )
     })

     @PostMapping(value = "")
     Long createEvent (NewEventDto newEvent);
}
