package de.ait.todo.dto;

import de.ait.todo.models.Room;
import de.ait.todo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewBookingDto {

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime cheCkIn;
    private LocalDateTime checkOut;
    private List<Integer> roomIds;
    private Long userId;
}
