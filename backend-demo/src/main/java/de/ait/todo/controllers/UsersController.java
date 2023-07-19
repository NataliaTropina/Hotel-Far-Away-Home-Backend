package de.ait.todo.controllers;

import de.ait.todo.controllers.api.UsersApi;
import de.ait.todo.dto.*;
import de.ait.todo.security.details.AuthenticatedUser;
import de.ait.todo.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UsersService usersService;


    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<ProfileDto> getProfile(AuthenticatedUser currentUser) {
        Long currentUserId = currentUser.getUser().getId();
        ProfileDto profile = usersService.getProfile(currentUserId);

        return ResponseEntity.ok(profile);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UsersPage> getAll() {
        return ResponseEntity
                .ok(usersService.getAll());
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserDto> deleteUserById(Long userId) {
       return ResponseEntity.ok(usersService.deleteUserById(userId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserDto> getUserById(Long userId) {
        return ResponseEntity.ok(usersService.getUserById(userId));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<UserDto> updateUserById(Long userId, NewUserDto newUserDto) {
        return ResponseEntity.ok(usersService.updateUserById(userId, newUserDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserDto> getUserByRoomId(Integer roomId) {
        return ResponseEntity.ok(usersService.getUserByRoomId(roomId));
    }

}
