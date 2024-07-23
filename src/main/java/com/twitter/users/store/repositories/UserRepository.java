package com.twitter.users.store.repositories;

import com.twitter.users.store.entities.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByName(@NonNull String name);

    @Query("SELECT s FROM UserEntity s " +
            "WHERE :isFiltered = FALSE " +
            "OR LOWER(s.name) LIKE LOWER(CONCAT('%', :filter, '%')) " +
            "ORDER BY s.name DESC")
    List<UserEntity> findAllByFilter(boolean isFiltered, String filter);

}
