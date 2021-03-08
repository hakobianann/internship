package com.internship.persistence.repository;

import com.internship.persistence.entity.UserEntity;
import com.internship.service.dto.UserDto;
import com.internship.service.model.UserWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u INNER JOIN u.listOfUserCommunities WHERE u.status = :status")
    List<UserEntity> findAllUsers(String status);

    List<UserEntity> findAllByStatus(String status);

    @Query(value = "SELECT * FROM \"user\" WHERE status = 'ACTIVE'",
            nativeQuery = true)
    List<UserEntity> findAllActiveUsersNativeQuery();

    @Query("SELECT new com.internship.service.model.UserWrapper(u.id," +
            "u.status," +
            "u.firstName) FROM UserEntity u")
    Page<UserWrapper> findAllWithPagination(Pageable pageable);
}
