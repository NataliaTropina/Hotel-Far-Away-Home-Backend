package de.ait.todo.services.impl;

import de.ait.todo.dto.*;
import de.ait.todo.exceptions.IncorrectDeleteException;
import de.ait.todo.exceptions.NotFoundException;
import de.ait.todo.models.Booking;
import de.ait.todo.models.Room;
import de.ait.todo.models.User;
import de.ait.todo.repositories.BookingsRepository;
import de.ait.todo.repositories.RoomsRepository;
import de.ait.todo.repositories.UsersRepository;
import de.ait.todo.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final RoomsRepository roomsRepository;

    private final BookingsRepository bookingsRepository;


    @Override
    public ProfileDto getProfile(Long currentUserId) {
        User user = usersRepository.findById(currentUserId)
                .orElseThrow(IllegalArgumentException::new);

        return ProfileDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }


    @Override
    public UsersPage getAll() {
        List<User> users = usersRepository.findAll();
        return UsersPage.builder()
                .data(UserDto.from(users))
                .build();

    }

    @Override
    public UserDto deleteUserById(Long userId) {

        User user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("user with id <" + userId + "> not found")
                );
        if (user.getRole() == User.Role.ADMIN) {
            throw new IncorrectDeleteException("Admin can not be deleted");
        }
        usersRepository.deleteById(userId);
        return UserDto.from(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("user with id <" + userId + "> not found"));

        return UserDto.from(user);
    }

    @Override
    public UserDto updateUserById(Long userId, NewUserDto newUserDto) {

        User user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("user with id <" + userId + "> not found")

                );

        user.setFirstName(newUserDto.getFirstName());
        user.setLastName(newUserDto.getLastName());
        user.setUpdatedDate(newUserDto.getUpdateDate());
        user.setEmail(newUserDto.getEmail());
        user.setPhone(newUserDto.getPhone());

        usersRepository.save(user);

        return UserDto.from(user);
    }

    @Override
    public UserDto getUserByRoomId(Integer roomId) {

        User user = new User();

        Room room = roomsRepository.findById(roomId)
                .orElseThrow(() ->
                        new NotFoundException("Room with id <" + roomId + "> not found")
                );

        List<Booking> bookings = bookingsRepository.findByRooms(room);

        for(Booking booking: bookings) {

            if (booking != null) {
                LocalDate currentDate = LocalDate.now();
                LocalDate checkInDate = booking.getCheCkIn();
                LocalDate checkOutDate = booking.getCheckOut();

                if (currentDate.isEqual(checkInDate) || currentDate.isEqual(checkOutDate) ||
                        (currentDate.isAfter(checkInDate) && currentDate.isBefore(checkOutDate))) {
                     user = booking.getUser();
                } else throw new NotFoundException("Room with id < " + roomId + "> in not busy");
            }


        }
        return UserDto.from(user);
    }


}
