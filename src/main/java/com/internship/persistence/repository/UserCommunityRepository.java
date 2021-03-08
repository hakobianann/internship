package com.internship.persistence.repository;

import com.internship.persistence.entity.UserCommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommunityRepository extends JpaRepository<UserCommunityEntity, Long> {

}
