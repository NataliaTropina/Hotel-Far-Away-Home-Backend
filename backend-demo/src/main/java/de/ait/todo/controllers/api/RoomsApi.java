package de.ait.todo.controllers.api;

import de.ait.todo.dto.*;
import de.ait.todo.models.Room;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
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
                                    schema = @Schema(implementation = RoomDto.class))
                    }
            )
    })

    @PostMapping(value = "")
    int createRoom (@RequestBody NewRoomDto newRoom);

    @Operation(summary = "Получение списка всех номеров гостиницы", description = "Доступно всем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с Страница с номерами гостиницы",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomDto.class))
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
    ResponseEntity<RoomDto> getRoomById (@PathVariable("id") int id);

    @Operation(summary = "Обновление номера гостиницы и сохранение в базу данных", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление номера гостиницы по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomDto.class))
                    }
            )
    })

    @PutMapping(value = "/{id}")
    ResponseEntity<RoomDto> updateRoom (@PathVariable("id") int id, @RequestBody NewRoomDto newRoom);

    @Operation(summary = "Удаление номера гостиницы по ID", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаление номера гостиницы по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomDto.class))
                    }
            )
    })

    @DeleteMapping(value = "/{id}")
    ResponseEntity<RoomDto> deleteRoom (@PathVariable("id") int id);


    @Operation(summary = "Получение доступных номеров в заданный период", description = "Доступна только зарегистрированному пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получение доступных номеров",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RoomDto.class))
                    }
            )
    })

    @GetMapping(value = "/available")
    List<RoomDto> getAvailableRooms (    @RequestParam(value = "checkInDate", required = true)
                                      @DateTimeFormat(pattern = "dd-MM-yyyy")
                                      @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Date should be in format dd-MM-yyyy") LocalDate checkInDate,

                                      @RequestParam(value = "checkOutDate", required = true)
                                      @DateTimeFormat(pattern = "dd-MM-yyyy")
                                      @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Date should be in format dd-MM-yyyy") LocalDate checkOutDate);

}
