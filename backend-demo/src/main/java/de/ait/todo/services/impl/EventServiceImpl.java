package de.ait.todo.services.impl;

import de.ait.todo.dto.EventDto;
import de.ait.todo.dto.EventsPage;
import de.ait.todo.dto.NewEventDto;
import de.ait.todo.exceptions.NotFoundException;
import de.ait.todo.models.Event;
import de.ait.todo.models.User;
import de.ait.todo.repositories.EventsRepository;
import de.ait.todo.repositories.UsersRepository;
import de.ait.todo.security.details.AuthenticatedUser;
import de.ait.todo.services.EventService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventsRepository eventsRepository;

    private final UsersRepository usersRepository;
    @Override
    public Long createEvent(@org.jetbrains.annotations.NotNull NewEventDto newEvent) {

        Long userId = newEvent.getUserId();

    User user = usersRepository.findById(userId)
            .orElseThrow(()->
                    new NotFoundException("user with id <" + userId + "> not found"));

        Event event = Event.builder()
                .createDate(LocalDate.now())
                .text(newEvent.getText())
                .pictureUrl(newEvent.getPictureUrl())
                .user(user)
                .build();

        return eventsRepository.save(event).getId();
    }

    @Override
    public EventsPage getAll() {

        List<Event> events = eventsRepository.findAll();

        return EventsPage
                .builder()
                .data(EventDto.from(events))
                .build();
    }

    @Override
    public List<EventDto> getUserEvents(Long currentUserId) {

        User user = usersRepository.findById(currentUserId).get();

        List<Event> eventsByUser = eventsRepository.findAllByUser(user);

        return EventDto.from(eventsByUser);
    }

    @Override
    public EventDto deleteEventById(Long eventId, AuthenticatedUser currentUser) {

        Event event = eventsRepository.findById(eventId)
                .orElseThrow(()->
                        new NotFoundException("Event with id <" + eventId + "> not found")
                        );
        User user = usersRepository.findById(currentUser.getUser().getId())
                        .orElseThrow(()->
                                new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                                );
        if(currentUser.getUser().getId().equals(user.getId()) || currentUser.getUser().getRole().equals(User.Role.ADMIN)) {

            eventsRepository.deleteById(eventId);
        }
        return EventDto.from(event);
    }

    @Override
    public EventDto updateEventById(AuthenticatedUser currentUser, Long eventId, NewEventDto newEvent) {

        Event event = eventsRepository.findById(eventId)
                .orElseThrow(()->
                        new NotFoundException("Event with id <" + eventId + "> not found"));

        User user = usersRepository.findById(currentUser.getUser().getId())
                        .orElseThrow(()->
                                new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                                );
        if (event.getUser().getId().equals(user.getId())) {

            event.setText(newEvent.getText());
            event.setPictureUrl(newEvent.getPictureUrl());

            eventsRepository.save(event);
        } else throw new  NotFoundException("Event is not available");

        return EventDto.from(event);
    }
}
