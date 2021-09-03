package com.org.skillzag.service;

import com.org.skillzag.service.dto.SubscriptionManagementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.domain.SubscriptionManagement}.
 */
public interface SubscriptionManagementService {

    /**
     * Save a subscriptionManagement.
     *
     * @param subscriptionManagementDTO the entity to save.
     * @return the persisted entity.
     */
    SubscriptionManagementDTO save(SubscriptionManagementDTO subscriptionManagementDTO);

    /**
     * Get all the subscriptionManagements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SubscriptionManagementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" subscriptionManagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubscriptionManagementDTO> findOne(Long id);

    /**
     * Delete the "id" subscriptionManagement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
