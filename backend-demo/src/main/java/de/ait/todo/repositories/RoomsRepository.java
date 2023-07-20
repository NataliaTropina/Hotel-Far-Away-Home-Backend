package de.ait.todo.repositories;

import de.ait.todo.dto.RoomDto;
import de.ait.todo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Room, Integer> {

    List<Room> findAllByTypeOfRoom (Room.TypeOfRoom typeOfRoom);


}
