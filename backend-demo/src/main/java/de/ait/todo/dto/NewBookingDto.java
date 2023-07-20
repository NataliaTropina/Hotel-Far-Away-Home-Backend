package de.ait.todo.dto;

import de.ait.todo.models.Room;
import de.ait.todo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewBookingDto {

   // private LocalDate createDate;
   // private LocalDate updateDate;
    private LocalDate cheCkIn;
    private LocalDate checkOut;
    private Long userId;
    private List<Integer> roomIds;
   // private Long userId;
}
