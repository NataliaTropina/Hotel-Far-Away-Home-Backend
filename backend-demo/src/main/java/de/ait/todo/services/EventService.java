package de.ait.todo.services;

import de.ait.todo.dto.NewEventDto;

public interface EventService {

    Long createEvent (NewEventDto newEvent);
}
