package de.ait.todo.repositories;

import de.ait.todo.models.Booking;
import de.ait.todo.models.Room;
import de.ait.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByRoomsContains (Room room);
    List<Booking> findAllByUser (User user);
}
