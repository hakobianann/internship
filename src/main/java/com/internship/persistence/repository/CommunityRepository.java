package com.internship.persistence.repository;

import com.internship.persistence.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {

}
