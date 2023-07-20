package de.ait.todo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewEventDto {

    private LocalDate createDate;
    private String text;
    private String pictureUrl;
    private Long userId;

}
