package com.org.skillzag.repository;

import com.org.skillzag.domain.SessionManagement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SessionManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SessionManagementRepository extends JpaRepository<SessionManagement, Long> {
}
