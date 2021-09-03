package com.org.skillzag.repository;

import com.org.skillzag.domain.SubscriptionManagement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SubscriptionManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubscriptionManagementRepository extends JpaRepository<SubscriptionManagement, Long> {
}
