package de.ait.todo.repositories;

import de.ait.todo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<Room, Integer> {


}
