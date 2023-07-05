package de.ait.todo.dto;

import de.ait.todo.models.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventDto {

    private Long id;
    private LocalDate createDate;
    private String text;
    private String pictureUrl;
    private Long userId;

    public static EventDto from (Event event) {
        return EventDto.builder()
                .id(event.getId())
                .createDate(event.getCreateDate())
                .text(event.getText())
                .pictureUrl(event.getPictureUrl())
                .userId(event.getId())
                .build();
    }

    public static List<EventDto> from (List<Event> events) {
        return events.stream().map(EventDto::from).collect(Collectors.toList());
    }
}
