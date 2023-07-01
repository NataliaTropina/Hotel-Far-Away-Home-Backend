package de.ait.todo.repositories;

import de.ait.todo.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, Long> {


}
