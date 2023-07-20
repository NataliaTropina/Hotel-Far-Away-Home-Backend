package de.ait.todo.advices;

import de.ait.todo.dto.StandardResponseDto;
import de.ait.todo.exceptions.IncorrectDeleteException;
import de.ait.todo.exceptions.RoomAlreadyBookedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.ait.todo.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<StandardResponseDto> handleNotFound(NotFoundException ex) {
        log.error(ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(StandardResponseDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build());
    }


        @ExceptionHandler(IncorrectDeleteException.class)
        public ResponseEntity<StandardResponseDto> handleIncorrectDeleteException(IncorrectDeleteException ex) {
            log.error(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(StandardResponseDto.builder()
                            .message(ex.getMessage())
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build());
        }

    @ExceptionHandler(RoomAlreadyBookedException.class)
    public ResponseEntity<Map<String, Object>> handleRoomAlreadyBookedException(RoomAlreadyBookedException ex) {
        log.error(ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}


