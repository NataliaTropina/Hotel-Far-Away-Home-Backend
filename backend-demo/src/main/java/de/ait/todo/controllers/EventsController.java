package de.ait.todo.controllers;

import de.ait.todo.controllers.api.EventsApi;
import de.ait.todo.dto.EventDto;
import de.ait.todo.dto.EventsPage;
import de.ait.todo.dto.NewEventDto;
import de.ait.todo.security.details.AuthenticatedUser;
import de.ait.todo.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventsController implements EventsApi {

    private final EventService eventService;

    @PreAuthorize("isAuthenticated()")
    @Override
    public Long createEvent (NewEventDto newEvent, AuthenticatedUser currentUser) {

        return eventService.createEvent(newEvent, currentUser);
    }

    @Override
    public EventsPage getAll() {
        return eventService.getAll();
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public List<EventDto> getUserEvents(AuthenticatedUser currentUser) {
        Long currentUserId = currentUser.getUser().getId();
        return eventService.getUserEvents(currentUserId);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Override
    public ResponseEntity<EventDto> deleteEventById(Long eventId, AuthenticatedUser currentUser) {
        return ResponseEntity.ok(eventService.deleteEventById(eventId, currentUser));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<EventDto> updateEventById(AuthenticatedUser currentUser , Long eventId, NewEventDto newEvent) {
        return ResponseEntity.ok(eventService.updateEventById(currentUser,eventId, newEvent));
    }
}
