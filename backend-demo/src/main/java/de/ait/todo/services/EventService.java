package de.ait.todo.services;

import de.ait.todo.dto.EventDto;
import de.ait.todo.dto.EventsPage;
import de.ait.todo.dto.NewEventDto;
import de.ait.todo.security.details.AuthenticatedUser;

import java.util.List;

public interface EventService {

    Long createEvent (NewEventDto newEvent);

    EventsPage getAll();

    List<EventDto> getUserEvents(Long currentUserId);

    EventDto deleteEventById(Long eventId, AuthenticatedUser currentUser);

    EventDto updateEventById(AuthenticatedUser currentUser, Long eventId, NewEventDto newEvent);
}
