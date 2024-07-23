package com.twitter.users.api.controller;

import com.twitter.users.api.dto.AckDto;
import com.twitter.users.api.dto.UserDto;
import com.twitter.users.api.exceptions.BadRequestException;
import com.twitter.users.api.factories.UserDtoFactory;
import com.twitter.users.store.entities.UserEntity;
import com.twitter.users.store.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class UserController {

    UserRepository userRepository;
    UserDtoFactory userDtoFactory;

    public static final String FETCH_USERS = "/api/users";
    public static final String CREATE_USER = "/api/users";
    public static final String DELETE_USERS = "/api/users/{userId}";

    @GetMapping(FETCH_USERS)
    public ResponseEntity<List<UserDto>> fetchUsers(@RequestParam String filter) {

        boolean isFiltered = !filter.trim().isEmpty();

        List<UserEntity> users = userRepository.findAllByFilter(isFiltered, filter);

        return ResponseEntity.ok(userDtoFactory.fetchUserDtoList(users));

    }

    @PostMapping(CREATE_USER)
    public ResponseEntity<UserDto> createUser(@RequestParam String name) {

        if (userRepository.existsByName(name)) {

            throw new BadRequestException(String.format("User _%s_ already exists", name));

        }

        UserEntity userEntity = userRepository.saveAndFlush(
                UserEntity.makeDefault(name)
        );

        return ResponseEntity.ok(userDtoFactory.createUserDto(userEntity));
    }

    @DeleteMapping(DELETE_USERS)
    public ResponseEntity<AckDto> deleteUser(@PathVariable Long userId) {

        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new BadRequestException(String.format("User with id _%s_ does not exist", userId));
        }

        return ResponseEntity.ok(AckDto.makeDefault(true));

    }
}
