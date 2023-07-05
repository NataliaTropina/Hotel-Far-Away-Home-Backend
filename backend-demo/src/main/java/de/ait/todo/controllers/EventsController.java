package de.ait.todo.controllers;

import de.ait.todo.controllers.api.EventsApi;
import de.ait.todo.dto.NewEventDto;
import de.ait.todo.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventsController implements EventsApi {

    private final EventService eventService;

    public Long createEvent (NewEventDto newEvent) {

        return eventService.createEvent(newEvent);
    }
}
