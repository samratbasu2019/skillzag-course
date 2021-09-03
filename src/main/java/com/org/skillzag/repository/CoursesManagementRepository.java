package com.org.skillzag.repository;

import com.org.skillzag.domain.CoursesManagement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CoursesManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoursesManagementRepository extends JpaRepository<CoursesManagement, Long> {
}
