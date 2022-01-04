package com.org.skillzag.repository;

import com.org.skillzag.domain.CourseActivity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CourseActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseActivityRepository extends JpaRepository<CourseActivity, Long> {
}
