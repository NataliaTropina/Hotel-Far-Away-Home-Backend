package de.ait.todo.controllers.api;

import de.ait.todo.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = {
        @Tag(name = "Rooms")
})
@RequestMapping("/api/rooms")
public interface RoomsApi {
    @Operation(summary = "Создание нового номера гостиницы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Новый номер гостиницы",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomsPage.class))
                    }
            )
    })

    @PostMapping(value = "")
    int createRoom (@RequestBody NewRoomDto newRoom);

    @Operation(summary = "Получение списка всех номеров гостиницы", description = "Доступно всем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с пользователями",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomsPage.class))
                    }
            )
    })

    @GetMapping(value = "")
    RoomsPage getAll ();


    @Operation(summary = "Получение номера гостиницы по ID", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Номер гостиницы по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomsPage.class))
                    }
            )
    })

    @GetMapping(value = "/{id}")
    ResponseEntity<RoomDto> getRoomById (@PathVariable int id);

    @Operation(summary = "Обновление номера гостиницы и сохранение в базу данных", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление номера гостиницы по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomsPage.class))
                    }
            )
    })

    @PutMapping(value = "/{id}")
    ResponseEntity<RoomDto> updateRoom (@PathVariable int id, @RequestBody NewRoomDto newRoom);

    @Operation(summary = "Удаление номера гостиницы по ID", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаление номера гостиницы по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomsPage.class))
                    }
            )
    })

    @DeleteMapping(value = "/{id}")
    ResponseEntity<RoomDto> deleteRoom (@PathVariable int id);
}
