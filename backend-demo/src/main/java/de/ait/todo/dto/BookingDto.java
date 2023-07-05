package de.ait.todo.dto;

import de.ait.todo.models.Booking;
import de.ait.todo.models.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDto {

    private Long id;
   // private LocalDate createDate;
   // private LocalDate updateDate;
    private LocalDate cheCkIn;
    private LocalDate checkOut;
    private List<Integer> roomIds;
    private Long userId;

    public static BookingDto from (Booking booking){
        return builder()
                .id(booking.getId())
                .cheCkIn(booking.getCheCkIn())
                .checkOut(booking.getCheckOut())
                .roomIds(booking.getRooms().stream().map(Room::getId).collect(Collectors.toList()))
                .build();

    }

    public static List<BookingDto> from (List<Booking> bookings){
        return bookings
                .stream()
                .map(BookingDto::from)
                .collect(Collectors.toList());
    }
}
