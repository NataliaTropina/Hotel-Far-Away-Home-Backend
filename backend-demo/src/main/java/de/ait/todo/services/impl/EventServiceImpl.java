package de.ait.todo.services.impl;

import de.ait.todo.dto.NewEventDto;
import de.ait.todo.exceptions.NotFoundException;
import de.ait.todo.models.Event;
import de.ait.todo.models.User;
import de.ait.todo.repositories.EventsRepository;
import de.ait.todo.repositories.UsersRepository;
import de.ait.todo.services.EventService;
import lombok.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventsRepository eventsRepository;

    private final UsersRepository usersRepository;
    @Override
    public Long createEvent(NewEventDto newEvent) {

        Long userId = newEvent.getUserId();

    User user = usersRepository.findById(userId)
            .orElseThrow(()->
                    new NotFoundException("user with id <" + userId + "> not found"));

        Event event = Event.builder()
                .createDate(newEvent.getCreateDate())
                .text(newEvent.getText())
                .pictureUrl(newEvent.getPictureUrl())
                .user(user)
                .build();

        return eventsRepository.save(event).getId();
    }
}
