package com.org.skillzag.service;

import com.org.skillzag.service.dto.SessionManagementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.domain.SessionManagement}.
 */
public interface SessionManagementService {

    /**
     * Save a sessionManagement.
     *
     * @param sessionManagementDTO the entity to save.
     * @return the persisted entity.
     */
    SessionManagementDTO save(SessionManagementDTO sessionManagementDTO);

    /**
     * Get all the sessionManagements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SessionManagementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sessionManagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SessionManagementDTO> findOne(Long id);

    /**
     * Delete the "id" sessionManagement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
