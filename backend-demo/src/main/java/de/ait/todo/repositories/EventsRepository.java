package de.ait.todo.repositories;

import de.ait.todo.models.Event;
import de.ait.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByUser (User user);
}
