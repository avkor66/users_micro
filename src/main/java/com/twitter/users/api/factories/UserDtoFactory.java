package com.twitter.users.api.factories;

import com.twitter.users.api.dto.UserDto;
import com.twitter.users.store.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoFactory {
        public UserDto createUserDto(UserEntity entity) {

            return UserDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .createdAt(entity.getCreatedAt())
                    .build();
        }

        public List<UserDto> fetchUserDtoList(List<UserEntity> entities) {

            return entities
                    .stream()
                    .map(this::createUserDto)
                    .collect(Collectors.toList());

        }
}
