package de.ait.todo.services;


import de.ait.todo.dto.*;

import java.util.List;

public interface UsersService {

    ProfileDto getProfile(Long currentUserId);

    UsersPage getAll();

    UserDto deleteUserById(Long userId);

    UserDto getUserById(Long userId);

    UserDto updateUserById(Long userId, NewUserDto newUserDto);

}
