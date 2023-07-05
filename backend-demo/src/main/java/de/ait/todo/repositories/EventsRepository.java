package de.ait.todo.repositories;

import de.ait.todo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {
}
