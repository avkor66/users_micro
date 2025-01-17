package com.twitter.users.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @JsonProperty("user_id")
    @NonNull
    Long id;

    @JsonProperty("user_name")
    @NonNull
    String name;

    @JsonProperty("created_ad")
    @NonNull
    Instant createdAt;

}
