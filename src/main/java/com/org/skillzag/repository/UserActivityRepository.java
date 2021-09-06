package com.org.skillzag.repository;

import com.org.skillzag.domain.UserActivity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
}
