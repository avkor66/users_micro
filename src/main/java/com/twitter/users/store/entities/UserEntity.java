package com.twitter.users.store.entities;

import com.twitter.users.api.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "name")
public class UserEntity {

    @Id
    @GeneratedValue
    Long id;

    @NonNull
    String name;

    @Builder.Default
    Instant createdAt = Instant.now();

    public static UserEntity makeDefault(String userName) {
        return builder()
                .name(userName)
                .build();
    }

}